## Context

The StreakDays widget is built with Jetpack Glance and backed by a `PeriodicWorkRequest` (WorkManager) that is intended to fire every midnight to refresh the streak count. The `StreakCalculator` correctly computes the streak dynamically using `LocalDate.now()`, so data correctness is not the issue — the issue is that the widget's `provideGlance()` is never re-triggered after the widget is first placed on the home screen.

Three root causes were identified:
1. `ExistingPeriodicWorkPolicy.KEEP` silently discards re-scheduling attempts on subsequent app launches, so if the initial schedule drifted or was killed, it is never corrected.
2. `updatePeriodMillis` is absent from `streak_days_widget_info.xml`, so the Android system never sends `APPWIDGET_UPDATE` broadcasts as a fallback.
3. `StreakDaysWidgetReceiver.onEnabled()` is not overridden, so adding the widget to the home screen does not trigger an immediate refresh.

## Goals / Non-Goals

**Goals:**
- Widget shows the correct streak count when first placed on the home screen
- Widget recovers to the correct streak count within 30 minutes even if the WorkManager job was missed
- Midnight WorkManager job is always scheduled from the correct next-midnight, recalculated on each app launch
- Transient WorkManager failures retry in a predictable, bounded window

**Non-Goals:**
- Real-time (sub-minute) widget refresh — not required and would drain battery
- Migrating from WorkManager to AlarmManager for exact-alarm precision
- Changing the streak calculation logic (already correct)
- Supporting multiple widgets of different types

## Decisions

### Decision 1: `CANCEL_AND_REENQUEUE` over `KEEP`

**Chosen**: Replace `ExistingPeriodicWorkPolicy.KEEP` with `ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE` in `WidgetUpdateScheduler`.

**Why**: `KEEP` preserves the original schedule even when it is stale or miscalculated. `CANCEL_AND_REENQUEUE` recalculates `initialDelay` from the current time to the next midnight on every app open, self-correcting any drift.

**Alternative considered**: `UPDATE` — preserves the period but replaces constraints. Rejected because it does not reset `initialDelay`, so the midnight drift is not corrected.

**Caveat**: `CANCEL_AND_REENQUEUE` cancels the in-flight run if the worker is actively executing at app open. This is acceptable because the worker is idempotent (calling `updateAll()` twice is safe) and the window is extremely narrow.

---

### Decision 2: `BackoffPolicy.LINEAR` at 15 minutes

**Chosen**: Add `.setBackoffCriteria(BackoffPolicy.LINEAR, 15, TimeUnit.MINUTES)` to the `PeriodicWorkRequestBuilder`.

**Why**: The default `EXPONENTIAL` backoff can delay retries by hours (30s → 60s → … → 5h cap). For a daily widget update, a transient failure at midnight should retry within 15 minutes — still within the "same midnight window" that the user cares about.

**Alternative considered**: Default exponential — rejected because a retry at 3 AM is effectively a missed update from the user's perspective.

---

### Decision 3: `updatePeriodMillis = 1800000` (30 minutes) as system-level fallback

**Chosen**: Add `android:updatePeriodMillis="1800000"` to `streak_days_widget_info.xml`.

**Why**: This activates the Android AppWidget framework's built-in broadcast mechanism. Every 30 minutes the system sends `APPWIDGET_UPDATE` to the receiver, which causes Glance to call `provideGlance()` again, re-executing `StreakCalculator.calculate(LocalDate.now())`. This is a pure safety net — it requires zero additional code beyond the XML attribute.

**Alternative considered**: `AlarmManager` with `setExactAndAllowWhileIdle` — provides exact midnight precision but requires `SCHEDULE_EXACT_ALARM` permission (restricted on Android 12+) and is significantly more complex. Rejected for this fix scope.

**Trade-off**: 30 minutes is the Android minimum; the widget may show the previous day's count for up to 30 minutes after midnight if both the WorkManager job and `onEnabled` paths fail. Acceptable for a streak counter.

---

### Decision 4: Override `onEnabled()` in `StreakDaysWidgetReceiver`

**Chosen**: Override `onEnabled()` to call `StreakDaysWidget().updateAll(context)` inside a `MainScope().launch {}`.

**Why**: `onEnabled` fires once when the first widget instance is added. This resolves the exact reported bug — user adds the widget and sees stale data until the next WorkManager tick or system update.

**Alternative considered**: `onUpdate()` override — fires on every update including the 30-min system pulse. Glance already handles this internally via the `GlanceAppWidgetReceiver` superclass, so overriding `onEnabled` is the minimal, non-duplicative intervention.

## Risks / Trade-offs

- **CANCEL_AND_REENQUEUE on every app open** → If the app is opened very frequently close to midnight, the job is repeatedly cancelled and rescheduled. Risk is low because WorkManager's `initialDelay` recalculation is cheap and the window to cancel an actively-running worker is milliseconds wide.
- **30-minute update period increases battery use** → Each system update wakes the app process to call `provideGlance()`. For a simple DB read + Glance render this is negligible, but worth monitoring if the widget becomes more complex.
- **`MainScope()` in receiver** → Coroutine launched in `onEnabled` uses `MainScope`, which is not lifecycle-aware. If the process dies mid-launch the update is lost — acceptable because the 30-min system fallback covers this gap.

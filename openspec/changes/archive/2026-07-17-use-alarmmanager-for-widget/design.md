## Context

The home screen widget freezes when the app falls into the "Rare" standby bucket, because the periodic background updates (using `WorkManager` and `ACTION_APPWIDGET_UPDATE`) are heavily restricted by the OS. The app is a "Days Since" tracker, meaning users rely heavily on the widget and rarely open the app, causing it to reach this standby bucket easily.

## Goals / Non-Goals

**Goals:**
- Guarantee widget updates at midnight regardless of app usage frequency.
- Immediately update the widget if the user opens the app (moves to foreground).
- Ensure the mechanism respects Android's background execution limits (Doze) without requiring special permissions that trigger Play Store scrutiny.

**Non-Goals:**
- Completely rewriting the `StreakDaysWidget` UI layer.
- Changing the frequency of widget updates to something other than midnight and app foreground events.

## Decisions

- **Use AlarmManager instead of WorkManager**: 
  - *Rationale*: WorkManager's `PeriodicWorkRequest` is deferred during Doze mode and App Standby, leading to missed midnight updates. `AlarmManager` with `setAndAllowWhileIdle` can reliably execute at a specific time (or close to it) and wake the device briefly to perform essential UI updates.
  - *Alternatives considered*: `setExactAndAllowWhileIdle`. Rejected because it requires the `SCHEDULE_EXACT_ALARM` permission on Android 12+, which Google Play restricts.

- **Trigger Update on App Foreground (onResume)**:
  - *Rationale*: If the widget is stuck for any reason (e.g., phone was completely dead, or system dropped the alarm), opening the app should instantly fix the state. By tying this to `MainActivity.onResume()`, the user gets immediate visual feedback that the widget is correct.

## Risks / Trade-offs

- [Risk] `setAndAllowWhileIdle` is inexact. It might trigger slightly after midnight instead of exactly at 00:00. 
  → Mitigation: This is acceptable for a daily streak tracker, as users are rarely watching the widget exactly at midnight, and waking the device perfectly on the dot is not strictly necessary for the experience.

- [Risk] Removing `WorkManager` could break other things if it was used elsewhere.
  → Mitigation: The dependency search showed that `WorkManager` is exclusively used for the widget update.

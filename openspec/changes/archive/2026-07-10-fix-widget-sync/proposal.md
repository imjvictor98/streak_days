## Why

The home screen widget does not update its streak count after midnight, causing users to see stale data (e.g., 2/14 days) while the app correctly shows the current value (3/14 days). This happens because the WorkManager periodic job uses `KEEP` policy — silently ignoring rescheduling on subsequent app launches — and no `updatePeriodMillis` is set in the widget provider XML, leaving no fallback update mechanism.

## What Changes

- Add `android:updatePeriodMillis="1800000"` to `streak_days_widget_info.xml` so the Android system triggers a native update every 30 minutes as a safety net
- Override `onEnabled()` in `StreakDaysWidgetReceiver` to force an immediate widget refresh when the widget is first added to the home screen
- Replace `ExistingPeriodicWorkPolicy.KEEP` with `CANCEL_AND_REENQUEUE` in `WidgetUpdateScheduler` so the midnight delay is recalculated every time the app opens
- Add an explicit `BackoffPolicy.LINEAR` with a 15-minute interval in the `PeriodicWorkRequest` so transient failures retry predictably without unbounded exponential delays

## Capabilities

### New Capabilities
- None

### Modified Capabilities
- `widget-daily-refresh`: The midnight scheduling policy changes from KEEP to CANCEL_AND_REENQUEUE, and the widget now has a 30-minute system-level update period as a redundant refresh mechanism. The onEnabled event now triggers an immediate update when the widget is added.

## Impact

- **Module affected**: `app` (single-module project)
- **Files changed**: `streak_days_widget_info.xml`, `StreakDaysWidgetReceiver.kt`, `WidgetUpdateScheduler.kt`, `WidgetDailyUpdateWorker.kt`
- **No new permissions required**
- **No new dependencies** — uses existing WorkManager and Glance APIs already declared in `build.gradle.kts`
- **No breaking changes** — all changes are additive or policy corrections
- **Min SDK**: unaffected; `updatePeriodMillis` and `onEnabled` are available since API 1

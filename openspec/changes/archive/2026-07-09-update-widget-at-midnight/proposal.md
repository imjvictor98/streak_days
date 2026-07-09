## Why

Currently, the StreakDays widget uses a `WorkManager` scheduled every 24 hours without an initial delay, causing it to update at the time it was initially launched instead of at midnight. This leads to a poor user experience as the widget does not immediately reflect a new day's progress at 00:00. This change ensures the widget refreshes soon after midnight by setting an `initialDelay`.

## What Changes

- Modify `StreakDaysApplication` to calculate the time remaining until the next midnight.
- Schedule the `WidgetDailyUpdateWorker` using the calculated time as the `initialDelay` for a `PeriodicWorkRequest`.
- Note: Android's Doze mode may delay the execution past exactly 00:00, but it will run at the earliest battery-friendly time the following morning.

## Capabilities

### New Capabilities
- `widget-daily-refresh`: Ensures the home screen widget is updated daily around midnight to accurately reflect the correct date and streak statuses without requiring the user to open the app.

### Modified Capabilities

## Impact

- **Module:** `app` module is affected.
- **Code:** Modifies the WorkManager initialization inside `StreakDaysApplication`.
- **Battery/System:** Relies on standard `WorkManager` behavior. No exact alarm permissions are required, maintaining battery efficiency.

## 1. Widget Worker Scheduling Update

- [x] 1.1 In `StreakDaysApplication.kt`, write a helper logic/function to calculate the duration between the current time and the next midnight (00:00:00).
- [x] 1.2 In `StreakDaysApplication.kt`, update the `PeriodicWorkRequestBuilder` for `WidgetDailyUpdateWorker` to use `setInitialDelay` with the calculated duration.
- [x] 1.3 In `StreakDaysApplication.kt`, change the enqueued unique work name to `"WidgetMidnightUpdate"` and use `ExistingPeriodicWorkPolicy.UPDATE` or `KEEP` to ensure the new schedule takes effect for existing users.

## 2. Verification

- [x] 2.1 Verify that the updated `StreakDaysApplication` schedules the initial delay correctly.

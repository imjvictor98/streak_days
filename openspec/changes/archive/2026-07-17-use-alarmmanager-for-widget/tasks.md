## 1. Clean up WorkManager

- [x] 1.1 Delete `WidgetDailyUpdateWorker.kt` since we will not be using WorkManager for the midnight update.
- [x] 1.2 Remove any WorkManager-related configuration from `WidgetUpdateScheduler.kt` and `StreakDaysApplication.kt`.

## 2. Implement MidnightUpdateReceiver

- [x] 2.1 Create `MidnightUpdateReceiver.kt` (extending `BroadcastReceiver`) and annotate with `@AndroidEntryPoint`.
- [x] 2.2 In `MidnightUpdateReceiver.onReceive`, trigger the widget update using `WidgetUpdater.updateGoalsWidget()`.
- [x] 2.3 Ensure `MidnightUpdateReceiver` reschedules the next alarm using `WidgetUpdateScheduler`.
- [x] 2.4 Register `MidnightUpdateReceiver` in `AndroidManifest.xml`.

## 3. Implement AlarmManager Scheduling

- [x] 3.1 Update `WidgetUpdateScheduler.kt` to inject `AlarmManager`.
- [x] 3.2 Implement `scheduleMidnightUpdate` using `AlarmManager.setAndAllowWhileIdle()` with an intent targeted at `MidnightUpdateReceiver`.

## 4. Implement Foreground Updates

- [x] 4.1 Update `MainActivity.kt` to inject `WidgetUpdater`.
- [x] 4.2 Override `onResume()` in `MainActivity` to launch a coroutine and call `widgetUpdater.updateGoalsWidget()`.

## 5. Verify Implementation

- [x] 5.1 Test AlarmManager scheduling by manually verifying if the PendingIntent is created and the receiver responds to it.
- [x] 5.2 Test foreground updates by placing the widget, opening the app, and verifying it updates.

## 1. Widget XML Configuration

- [x] 1.1 Add `android:updatePeriodMillis="1800000"` to `app/src/main/res/xml/streak_days_widget_info.xml`

## 2. WorkManager Scheduling Fix

- [x] 2.1 Replace `ExistingPeriodicWorkPolicy.KEEP` with `ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE` in `WidgetUpdateScheduler.kt`
- [x] 2.2 Add `.setBackoffCriteria(BackoffPolicy.LINEAR, 15, TimeUnit.MINUTES)` to the `PeriodicWorkRequestBuilder` in `WidgetUpdateScheduler.kt`

## 3. Widget Receiver — Immediate Refresh on Add

- [x] 3.1 Override `onEnabled(context: Context)` in `StreakDaysWidgetReceiver.kt` to call `StreakDaysWidget().updateAll(context)` inside `MainScope().launch {}`

## 4. Verification

- [x] 4.1 Manually add the widget to the home screen and verify it displays the correct streak count immediately (without waiting for WorkManager)
- [x] 4.2 Verify in Android Studio's WorkManager inspector that a `WidgetMidnightUpdate` periodic job is enqueued with a delay close to the next midnight
- [x] 4.3 Open the app a second time and verify in the WorkManager inspector that the job was cancelled and re-enqueued with a fresh delay

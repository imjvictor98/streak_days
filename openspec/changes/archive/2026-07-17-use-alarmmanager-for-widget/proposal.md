## Why

The home screen widget freezes on the last day of the week if the user does not open the app. This happens because the app falls into Android's "Rare" App Standby Bucket after several days of inactivity, which aggressively throttles the `WorkManager` periodic updates and `ACTION_APPWIDGET_UPDATE` broadcasts. We need to switch to `AlarmManager` for more reliable midnight updates and ensure the widget updates when the app is brought to the foreground.

## What Changes

- Replace `WorkManager` with `AlarmManager` for scheduling the midnight update of the widget.
- Use `setAndAllowWhileIdle` to ensure the alarm can wake up the device from Doze mode.
- Introduce a new `BroadcastReceiver` (`MidnightUpdateReceiver`) to handle the alarm and trigger the widget update.
- Add a trigger to update the widget when `MainActivity` is resumed (`onResume()`), ensuring the widget is always fresh if the user opens the app.
- Delete the old `WidgetDailyUpdateWorker`.

## Capabilities

### Modified Capabilities
- `widget-daily-refresh`: Modifying the widget daily refresh capability to use AlarmManager instead of WorkManager, and adding foreground update support.

## Impact

- **Affected Code**: `WidgetUpdateScheduler`, `MainActivity`, `AndroidManifest.xml`.
- **Removed Code**: `WidgetDailyUpdateWorker`.
- **Dependencies**: The `androidx.work:work-runtime-ktx` dependency could potentially be removed if it's no longer used anywhere else in the app.
- **Permissions**: No new permissions required. `setAndAllowWhileIdle` is used instead of exact alarms to avoid needing `SCHEDULE_EXACT_ALARM`.

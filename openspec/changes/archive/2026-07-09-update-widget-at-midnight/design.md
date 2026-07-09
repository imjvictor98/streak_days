## Context

The StreakDays app has a home screen widget (implemented with Glance) that displays the user's progress. Currently, a WorkManager task (`WidgetDailyUpdateWorker`) is scheduled in `StreakDaysApplication` to update this widget every 24 hours. Because it lacks an initial delay, it triggers exactly 24 hours after the application was started, rather than at midnight. This creates a confusing experience where the widget doesn't immediately reflect the new day's progress.

## Goals / Non-Goals

**Goals:**
- Ensure the widget refreshes soon after midnight to accurately reflect the new day's streak state.
- Keep the background work battery-friendly.

**Non-Goals:**
- Implement exact-to-the-second midnight updates which would require exact alarm permissions.
- Change the widget's UI layout or rendering logic.

## Decisions

- **Initial Delay Calculation**: We will calculate the duration between the current time and the next midnight (00:00:00 of the following day). This value will be passed as `initialDelay` to the `PeriodicWorkRequestBuilder`.
- **Why WorkManager?**: Although `WorkManager` (especially on Doze mode) does not guarantee exact timing, it is the recommended approach for daily syncs because it is battery-friendly and requires no extra permissions compared to `AlarmManager` with exact alarms on Android 12+.
- **Handling App Upgrades**: We will change the `ExistingPeriodicWorkPolicy` from `KEEP` to `UPDATE` (if using recent WorkManager version) or change the unique work name to ensure existing users get the new schedule when they update the app. We'll use the unique name `"WidgetMidnightUpdate"` to force a reschedule.

## Risks / Trade-offs

- **Risk:** Android Doze mode may postpone the execution past exactly 00:00.
  - **Mitigation:** This is an acceptable trade-off for battery life. The widget will update as soon as the phone enters maintenance window or is woken up in the morning.

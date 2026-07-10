## MODIFIED Requirements

### Requirement: Midnight Widget Sync
The system SHALL schedule the daily widget update worker to execute shortly after midnight using `CANCEL_AND_REENQUEUE` policy so that the midnight delay is always recalculated from the current app launch time. The worker SHALL use a `LINEAR` backoff policy of 15 minutes on failure.

#### Scenario: Initial worker scheduling
- **WHEN** the application starts and schedules the daily worker
- **THEN** the initial delay of the periodic worker SHALL be calculated as the time remaining until the next midnight

#### Scenario: Rescheduling on subsequent launches
- **WHEN** the application starts and a worker named `WidgetMidnightUpdate` already exists
- **THEN** the existing worker SHALL be cancelled and a new one enqueued with a fresh midnight delay calculation

#### Scenario: Worker failure and retry
- **WHEN** `WidgetDailyUpdateWorker.doWork()` throws an exception
- **THEN** the worker SHALL return `Result.retry()` and the system SHALL retry after 15 minutes using a LINEAR backoff policy

## ADDED Requirements

### Requirement: Immediate Widget Refresh on Add
The system SHALL trigger an immediate widget data refresh whenever the first instance of the streak widget is added to the home screen.

#### Scenario: Widget placed on home screen
- **WHEN** the user adds the streak widget to the home screen for the first time
- **THEN** `StreakDaysWidget().updateAll()` SHALL be called immediately so the widget displays the current streak count without waiting for the next scheduled refresh

### Requirement: System-Level Periodic Widget Refresh
The system SHALL configure the widget provider to receive Android system update broadcasts every 30 minutes as a fallback refresh mechanism.

#### Scenario: System update broadcast received
- **WHEN** the Android system fires an `APPWIDGET_UPDATE` broadcast (every 30 minutes)
- **THEN** Glance SHALL re-execute `provideGlance()`, causing `StreakCalculator.calculate(LocalDate.now())` to run and the widget to display the updated streak count

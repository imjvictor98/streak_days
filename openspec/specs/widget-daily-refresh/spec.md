# Capability: Widget Daily Refresh

## Purpose
TBD: Manage the daily updates and synchronizations of the application widget.

## Requirements

### Requirement: Midnight Widget Sync
The system SHALL schedule the daily widget update to execute exactly at midnight using `AlarmManager.setAndAllowWhileIdle()` to ensure it fires reliably even if the device is in Doze Mode or App Standby. The scheduled intent MUST trigger a BroadcastReceiver that updates the widget and reschedules the alarm for the subsequent midnight.

#### Scenario: Initial alarm scheduling
- **WHEN** the application starts or the widget is updated
- **THEN** an inexact alarm SHALL be scheduled using `setAndAllowWhileIdle` for the exact time of the next midnight

#### Scenario: Rescheduling on alarm trigger
- **WHEN** the `MidnightUpdateReceiver` is triggered by the alarm
- **THEN** it SHALL update all widget instances AND immediately schedule the next alarm for the following midnight

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

### Requirement: Immediate Widget Refresh on App Open
The system SHALL force an immediate update of the home screen widget whenever the application is brought to the foreground, to ensure users never see stale data if background alarms are aggressively suppressed by the OS.

#### Scenario: User opens the app
- **WHEN** `MainActivity` enters the `onResume` lifecycle state
- **THEN** the system SHALL immediately execute `updateGoalsWidget()` to refresh the widget UI

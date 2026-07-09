## ADDED Requirements

### Requirement: Calendar Day Streak Calculation
The system SHALL calculate streak length based on calendar days (midnight-to-midnight in the local timezone), rather than 24-hour intervals.

#### Scenario: Streak increments on new calendar day
- **GIVEN** a goal started at 11:00 PM on Day 1
- **WHEN** the system time becomes 12:01 AM on Day 2 in the local timezone
- **THEN** the streak increments by 1 day, displaying a total streak of 1 day

### Requirement: Logging Relapse Events
The system SHALL allow users to log a relapse event for a specific date, which resets the current streak.

#### Scenario: Logging a relapse
- **GIVEN** a goal has an active streak of 10 days
- **WHEN** the user logs a relapse event for today
- **THEN** the current streak counter resets to 0 days, and the 10-day streak is saved to history

### Requirement: Streak History Tracking
The system SHALL maintain a history of past streaks (start date, relapse date, length) for each goal.

#### Scenario: Viewing past streaks
- **GIVEN** a goal has experienced previous relapses
- **WHEN** the user views the details screen for that goal
- **THEN** a list of past streaks, including their durations and dates, is displayed alongside the longest streak record

## ADDED Requirements

### Requirement: Automatic streak calculation
The system SHALL compute the current streak count dynamically based on the difference in calendar days between the current date and the start date (or last relapse date), starting at 1 for the first day.

#### Scenario: User starts a new streak today
- **WHEN** the user begins a streak or has a relapse today
- **THEN** the system immediately displays a streak count of 1

#### Scenario: User checks streak the next day
- **WHEN** the user opens the app on the calendar day following the start date
- **THEN** the system displays a streak count of 2

#### Scenario: User checks streak after one week
- **WHEN** the user opens the app 7 calendar days after the start date
- **THEN** the system displays a streak count of 8

## MODIFIED Requirements

### Requirement: Automatic streak calculation
The system SHALL compute the current streak count dynamically based on the difference in calendar days between the current date and the start date (or last relapse date), starting at 1 for the first day. 

**Added Edge Case:** If the current streak is 0, the system SHALL consider that there are no completed days in the current streak (avoiding invalid future start date calculations).

#### Scenario: User starts a new streak today
- **WHEN** the user begins a streak or has a relapse today
- **THEN** the system immediately displays a streak count of 1

#### Scenario: User checks streak the next day
- **WHEN** the user opens the app on the calendar day following the start date
- **THEN** the system displays a streak count of 2

#### Scenario: User checks streak after one week
- **WHEN** the user opens the app 7 calendar days after the start date
- **THEN** the system displays a streak count of 8

#### Scenario: Zero days completed
- **WHEN** the user has a current streak of 0
- **THEN** the system considers the streak to have no completed days and no valid start date

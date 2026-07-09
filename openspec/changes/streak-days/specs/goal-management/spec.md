## ADDED Requirements

### Requirement: Goal Creation
The system SHALL allow users to create a new goal with a name, target duration, and start date.

#### Scenario: Successful goal creation
- **GIVEN** the user is on the goal creation screen
- **WHEN** the user inputs a valid name, selects a target duration, sets a start date, and clicks save
- **THEN** the goal is saved and appears in the goals list

### Requirement: Multiple Concurrent Goals
The system SHALL support tracking multiple active goals simultaneously.

#### Scenario: Viewing multiple goals
- **GIVEN** the user has created multiple goals
- **WHEN** the user opens the main dashboard
- **THEN** a list of all active goals is displayed

### Requirement: Goal Completion State
The system SHALL mark a goal as completed when the target duration is reached without a relapse, and optionally allow the user to continue tracking.

#### Scenario: Reaching target duration
- **GIVEN** a goal has an active streak that meets or exceeds the target duration
- **WHEN** the streak is calculated for the current day
- **THEN** the UI reflects the goal's completed status but continues incrementing the streak if the user hasn't stopped it

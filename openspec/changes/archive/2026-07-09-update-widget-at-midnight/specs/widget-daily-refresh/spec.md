## ADDED Requirements

### Requirement: Midnight Widget Sync
The system SHALL schedule the daily widget update worker to execute shortly after midnight.

#### Scenario: Initial worker scheduling
- **WHEN** the application starts and schedules the daily worker
- **THEN** the initial delay of the periodic worker SHALL be calculated as the time remaining until the next midnight

#### Scenario: Existing worker update
- **WHEN** a user updates to this version
- **THEN** the existing worker SHALL be replaced or rescheduled to ensure the midnight timing applies immediately

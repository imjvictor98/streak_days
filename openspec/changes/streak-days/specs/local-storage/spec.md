## ADDED Requirements

### Requirement: Persistent Storage of Goals and Relapses
The system SHALL persist all goal definitions and logged relapse events locally on the device.

#### Scenario: App restart retains data
- **GIVEN** the user has created a goal and logged a relapse
- **WHEN** the user closes and reopens the application
- **THEN** the goal and its history are loaded from local storage and displayed accurately

### Requirement: Offline Functionality
The system SHALL function completely offline without relying on any backend or cloud sync.

#### Scenario: Calculating streaks without internet
- **GIVEN** the device is in airplane mode
- **WHEN** the user opens the app on a new calendar day
- **THEN** the streak is calculated correctly based on local time and stored data

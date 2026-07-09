## ADDED Requirements

### Requirement: Modern Goal Card Display
The system SHALL present each goal using a modern visual card that highlights streak progress using a visual gauge (like a circular progress indicator) and premium typography.

#### Scenario: Display active goal with progress
- **WHEN** the dashboard loads an active goal
- **THEN** it displays the goal's name clearly
- **THEN** it shows a visual indicator (e.g., circular progress ring) reflecting `currentStreakDays` against `targetDurationDays`
- **THEN** it displays text stats for "Current Streak" and "Longest Streak"

#### Scenario: Display completed goal
- **WHEN** the dashboard loads a goal where `isCompleted` is true
- **THEN** the visual progress indicator shows 100% completion
- **THEN** it emphasizes the achievement state (e.g., "Target Achieved! 🎉") and uses a visually distinct styling

### Requirement: Dark Mode Support
The system SHALL ensure that the new dashboard UI looks premium and maintains proper contrast in both Light and Dark mode.

#### Scenario: User toggles Dark Mode
- **WHEN** the device configuration changes to Dark Mode
- **THEN** the goal cards adapt their background color and typography to the dark color scheme seamlessly without loss of readability

### Requirement: Empty State Redesign
The system SHALL provide a clear and encouraging empty state when there are no goals to display.

#### Scenario: User has zero goals
- **WHEN** the dashboard loads with an empty `goals` list
- **THEN** it displays a placeholder text "No goals yet. Tap + to create one!" centered on the screen, matching the new typography style

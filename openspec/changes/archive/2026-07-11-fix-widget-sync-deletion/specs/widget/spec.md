## ADDED Requirements

### Requirement: Widget updates on goal deletion
The system SHALL update the home screen widget immediately after a goal is successfully deleted.

#### Scenario: Deleting an active goal
- **WHEN** the user deletes an active goal
- **THEN** the system removes the goal from the local database
- **AND THEN** the system triggers a widget update to reflect the changes

### Requirement: Widget empty state text
The system SHALL display a conversational empty state text on the widget when there are no active goals.

#### Scenario: Viewing widget with zero goals
- **WHEN** the widget is displayed and the user has no active goals
- **THEN** the widget displays the text "Você não tem nenhum objetivo ainda"

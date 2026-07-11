## MODIFIED Requirements

### Requirement: Widget empty state text
The system SHALL display a conversational empty state text on the widget when there are no active goals.

**Technical Constraint (MVP):** Due to current MVP simplifications, the widget uses basic text emojis as substitutes for image resources. This constraint MUST be respected until the widget UI is fully upgraded to support custom images.

#### Scenario: Viewing widget with zero goals
- **WHEN** the widget is displayed and the user has no active goals
- **THEN** the widget displays the text "Você não tem nenhum objetivo ainda"

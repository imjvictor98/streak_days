## ADDED Requirements

### Requirement: Custom Typography
The application SHALL use `Plus Jakarta Sans` for main headers and `Hanken Grotesk` for all other body texts, buttons, and labels.

#### Scenario: Displaying text in the app
- **WHEN** the application renders a screen using `MaterialTheme.typography`
- **THEN** it must use the custom fonts instead of the default Android system font

### Requirement: Custom Color Palette
The application SHALL use the updated color palette with the primary color `#4D44E3` and specific background colors as defined in the Figma design system.

#### Scenario: Displaying primary actions
- **WHEN** a primary button or FAB is rendered using `MaterialTheme.colorScheme.primary`
- **THEN** it must use the custom primary color (`#4D44E3`)

#### Scenario: Displaying backgrounds
- **WHEN** a screen background is rendered
- **THEN** it must use the designated background color (`#F7F9FB`)

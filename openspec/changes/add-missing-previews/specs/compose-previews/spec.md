## ADDED Requirements

### Requirement: Jetpack Compose Previews
The system MUST include `@Preview` annotated functions for all Jetpack Compose screens and reusable UI components. These previews MUST be wrapped in the application's core theme to accurately render the UI as it would appear on a device.

#### Scenario: Visualizing a screen in the IDE
- **WHEN** a developer opens a Jetpack Compose screen file (e.g., `DashboardScreen.kt`)
- **THEN** the IDE's Compose Preview pane displays a visual representation of the screen in its default state, styled with the application's theme.

#### Scenario: Supporting Dark Mode Previews
- **WHEN** a component or screen is previewed
- **THEN** an additional preview configuration may be provided to visualize the component in Dark Mode (`uiMode = Configuration.UI_MODE_NIGHT_YES`).

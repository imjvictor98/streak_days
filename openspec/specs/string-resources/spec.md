## ADDED Requirements

### Requirement: Android String Resources Standardization
The system SHALL NOT contain any hardcoded user-facing text strings in its Kotlin/Compose code. All textual content intended for the user MUST be defined in standard Android string resources (`res/values/strings.xml`).

#### Scenario: Displaying static text in Compose
- **WHEN** a UI component renders static text (e.g. titles, buttons)
- **THEN** the text must be retrieved using `stringResource(R.string.string_name)`

#### Scenario: Displaying dynamic text in Compose
- **WHEN** a UI component renders text with arguments (e.g. "Streak: 5 days")
- **THEN** the text must be retrieved using format arguments, like `stringResource(R.string.streak_count, days)` where `strings.xml` defines `<string name="streak_count">Streak: %1$d days</string>`

#### Scenario: Displaying text from ViewModel (fallback)
- **WHEN** a ViewModel must construct a text message to be passed to the UI
- **THEN** the ViewModel must receive an application context or resource provider to resolve strings using `context.getString(R.string.string_name)`

### Requirement: String Resource Naming Convention
String keys in `strings.xml` SHALL follow a consistent naming convention that indicates their screen context or specific generic use case.

#### Scenario: Naming a new string
- **WHEN** a developer extracts a hardcoded string
- **THEN** they must name it following the pattern `<screen_or_component>_<element>` (e.g., `home_title`, `settings_save_button`, `error_generic`)

### Requirement: English Language Only
All textual content in the application MUST be written in English.

#### Scenario: Adding a new string
- **WHEN** a new user-facing string is added to the application
- **THEN** it must be written in English, regardless of the developer's primary language

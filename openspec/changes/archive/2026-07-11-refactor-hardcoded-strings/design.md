## Context

The current Android project (`StreakDays`) uses hardcoded strings in its UI layers (primarily Jetpack Compose screens). In Android development, text displayed to users should be extracted to resource files (`res/values/strings.xml`). This separation of concerns avoids repeating text, allows easy refactoring of text content, and is essential to enable internationalization/localization.

## Goals / Non-Goals

**Goals:**
- Move all user-facing hardcoded text into `res/values/strings.xml`.
- Replace hardcoded strings in the codebase with `stringResource(id = R.string.string_name)` for Compose, or `context.getString(R.string.string_name)` when in ViewModels (if necessary, though UI strings should ideally stay in the UI layer).
- Ensure consistent string naming convention inside `strings.xml`.

**Non-Goals:**
- Implementing translations to other languages (we are just extracting strings to the default `strings.xml`).
- Changing the underlying UI designs or architectures.

## Decisions

- **String Naming Convention**: Strings will be named based on their context or screen (e.g., `home_title`, `settings_button_save`, `error_generic`). This prevents generic names from clashing or being used in unintended contexts.
- **Compose UI Extraction**: For text directly displayed via `Text("Some text")`, it will be changed to `Text(stringResource(R.string.some_text))`.
- **String Formatting**: Strings that contain dynamic arguments will use format args (e.g., `<string name="welcome_message">Welcome, %1$s!</string>`) and accessed using `stringResource(R.string.welcome_message, userName)`.

## Risks / Trade-offs

- **Risk:** Potential syntax errors during mass replacement.
  **Mitigation:** Verify changes screen by screen and run the app/tests to ensure UI remains intact.
- **Trade-off:** slightly more code (adding `stringResource` overhead), but it's the standard platform approach so the maintainability benefit outweighs the verbosity.

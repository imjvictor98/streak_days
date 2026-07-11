## Why

Currently, the Android application uses hardcoded strings across its components and framework elements. This goes against Android's best practices, where text should be extracted into `strings.xml`. Using `strings.xml` improves maintainability, enables future localization, and keeps the presentation logic separate from content. 

## What Changes

- Extract all hardcoded user-facing strings in Jetpack Compose UI components and traditional Views/XMLs (if any) into `res/values/strings.xml`.
- Update the codebase to reference these strings using `stringResource(R.string.id)` in Compose or `context.getString(R.string.id)` elsewhere.
- Establish a standard pattern in the application for string handling moving forward.

## Capabilities

### New Capabilities
- `string-resources`: Defines the standard for extracting and consuming text resources via Android's `strings.xml` instead of using hardcoded strings.

### Modified Capabilities

- None

## Impact

- **Affected Modules**: `app` module (all Jetpack Compose screens and ViewModels presenting text).
- **Style and Maintenance**: The UI code will be cleaner and all user-facing text will be centralized in `res/values/strings.xml`.
- **Localization**: The application will be ready for easy localization in the future.

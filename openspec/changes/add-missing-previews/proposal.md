## Why

Currently, the StreakDays application uses Jetpack Compose for its UI but lacks `@Preview` functions for its screens and reusable components. Adding Jetpack Compose previews will significantly improve the developer experience by allowing rapid UI iteration and visual verification without needing to build and run the app on an emulator or physical device.

## What Changes

- Add Jetpack Compose `@Preview` functions to all existing screens (`DashboardScreen`, `CreateGoalScreen`, `GoalDetailsScreen`).
- Add `@Preview` functions for any reusable UI components.
- Ensure previews are wrapped in the application's `StreakDaysTheme` to render with correct styling.

## Capabilities

### New Capabilities
- `compose-previews`: Documentation and guidelines for using Jetpack Compose Previews across the application, ensuring that all UI components have visual previews for rapid development.

### Modified Capabilities

## Impact

- **Affected code**: `app/src/main/java/com/assabloy/livvi/streakdays/ui/dashboard/DashboardScreen.kt`, `app/src/main/java/com/assabloy/livvi/streakdays/ui/create/CreateGoalScreen.kt`, `app/src/main/java/com/assabloy/livvi/streakdays/ui/details/GoalDetailsScreen.kt`.
- **Dependencies**: No new dependencies required.
- **SDK/Permissions**: No changes to minSdk/targetSdk or AndroidManifest required.
- **Modules**: Modifications are scoped to the `app` module.

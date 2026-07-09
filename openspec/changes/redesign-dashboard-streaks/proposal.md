## Why

The current dashboard screen displays basic text information about goals, current streaks, and longest streaks inside simple cards. To increase user engagement and retention, we need a more visually appealing, modern UX/UI for displaying streaks inspired by high-quality design references. This will provide users with better visual feedback on their progress and a more premium feel.

## What Changes

- Redesign `DashboardScreen` and `GoalCard` to match the modern aesthetic of the provided reference.
- Introduce advanced visual indicators (e.g., circular progress, modern typography, improved spacing and colors) for tracking streak progress.
- Enhance the empty state and overall layout.
- Ensure all new Composable screens and reusable components include a `@Preview` function to support robust UI development across Light and Dark modes.
- The `app` Gradle module is the primary affected module. No new SDK requirements or permissions are anticipated unless a specific new charting library is introduced.

## Capabilities

### New Capabilities
- `dashboard-ui`: Revamp the visual presentation of goals and streaks on the main dashboard. This covers the visual layout, typography, and progress indicators for the user's active goals and streaks.

### Modified Capabilities

## Impact

- **UI/UX**: Significant improvement to the main landing screen (`DashboardScreen.kt`).
- **Code**: `GoalCard` and related composables in the dashboard package will be heavily modified or rewritten.
- **Dependencies**: May require adding a new compose dependency if we decide to use a specific charting or progress indicator library not currently in the project.

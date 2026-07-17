## Why

The current screens need to be refactored to match the updated Figma designs. This is crucial to ensure a consistent, premium user experience and to strictly adhere to the project's OpenSpec guidelines regarding the Compound Components pattern, keeping UI implementation perfectly separated from logic.

## What Changes

- Extract core UI components (TopAppBar, TextFields, Buttons) into `core/ui` using updated Figma design tokens (colors, typography).
- Update typography tokens in the application Theme (Plus Jakarta Sans, Hanken Grotesk, Inter, Be Vietnam Pro).
- Refactor `GoalCard` to follow the updated Compound Components pattern and design structure.
- Refactor `DashboardScreen` (`feature/dashboard`) to use the new `GoalCard` and core UI components.
- Refactor `CreateScreen` (`feature/create`) to use the new form container, inputs, and buttons according to the new Figma layout.

## Capabilities

### New Capabilities

### Modified Capabilities
- `compose-guidelines`: Updates to specific typography usage and specific component APIs (`StreakTopAppBar`, `StreakPrimaryButton`, `StreakTextField`) that were added based on the Figma design system.

## Impact

- **UI Core**: `core/ui/components` will receive new atomic components (`StreakTopAppBar`, `StreakPrimaryButton`, `StreakOutlineButton`, `StreakTextField`, `StreakFloatingActionButton`).
- **Features**: `feature/dashboard/ui` and `feature/create/ui` will be refactored, impacting visual presentation but maintaining existing ViewModel/Flow logic.
- **Theme**: The Compose Theme will be updated to include new font families. All screens will need to inherit these changes.
- **Dependencies**: No new external dependencies are expected, assuming fonts are downloaded or provided via standard resources.

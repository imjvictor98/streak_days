## Why

The current `GoalCard` UI component in `DashboardScreen` is monolithic and tightly coupled to the `Goal` domain entity. It renders multiple distinct elements (Title, Progress Ring, Stats Text, and Log Relapse button) internally without providing flexibility for parent composables to alter its content or structure. Refactoring this into the Compound Components pattern decouples the UI from domain logic and adheres to our new global Compose architectural guidelines, enabling high reusability and clear declarative structure.

## What Changes

- Refactor the monolithic `GoalCard` in the `app` module into a Compound Component (`GoalCard` with `GoalCardScope`).
- Extract the interface (`GoalCardScope.kt`) and implementation (`GoalCardScopeImpl.kt`) into separate files, following the global compose-guidelines.
- Update `DashboardScreen.kt` to construct the `GoalCard` using the new scoped DSL block, composing elements like `Header`, `ProgressRing`, `StatsRow`, and `ActionArea`.
- Ensure all refactored composables are accompanied by a `@Preview` function.

## Capabilities

### New Capabilities

### Modified Capabilities
- `compose-guidelines`: The new Compound Components pattern guidelines (added globally) are being applied to the Dashboard feature.

## Impact

- **Affected Modules**: `app` module (specifically `feature/dashboard` and possibly a shared UI package if the card is elevated).
- **Affected Screens**: `DashboardScreen` and its internal logic.
- **Dependencies & SDK**: No changes to min SDK or dependencies. No new permissions.
- **Testing**: Previews will be updated for the new `GoalCard` components and variations (active, completed).

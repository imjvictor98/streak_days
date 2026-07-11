## 1. Scope Interface and Implementation

- [x] 1.1 Create `GoalCardScope.kt` with the `GoalCardScope` interface in `feature/dashboard/ui/components` (or appropriate UI folder). Define `@Composable` slots for `Header`, `ProgressRing`, `StatsRow`, and `ActionArea`.
- [x] 1.2 Create `GoalCardScopeImpl.kt` implementing `GoalCardScope` in the same package.

## 2. Component Refactoring

- [x] 2.1 Refactor the existing `StreakProgressRing` and `StreakStatsText` from `DashboardScreen.kt` into reusable, standalone composables if they aren't already, or move them to the `components` folder.
- [x] 2.2 Create `GoalCard.kt` that accepts `content: @Composable GoalCardScope.() -> Unit` and uses `GoalCardScopeImpl` to inject the composables.
- [x] 2.3 Implement the internal layout for `GoalCard` so it correctly renders the UI elements provided via the scope DSL.
- [x] 2.4 Add `@Preview` functions to `GoalCard.kt` demonstrating an active card and a completed card.

## 3. Dashboard Screen Integration

- [x] 3.1 Update `DashboardScreen.kt` to use the new Compound `GoalCard` DSL instead of the old monolithic `GoalCard`.
- [x] 3.2 Ensure the `DashboardScreen` `@Preview` functions still render correctly after the update.
- [x] 3.3 Run UI tests or manually verify the screen renders accurately on a device/emulator.

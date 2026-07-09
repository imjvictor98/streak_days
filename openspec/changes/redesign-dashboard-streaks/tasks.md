## 1. Sub-components Implementation

- [x] 1.1 Create `StreakProgressRing` Composable with Canvas and proper `@Preview` functions
- [x] 1.2 Create `StreakStatsText` Composable to display typography-heavy stats with proper `@Preview` functions

## 2. Card Redesign

- [x] 2.1 Refactor `GoalCard` in `DashboardScreen.kt` to use the new sub-composables
- [x] 2.2 Implement distinct styles in `GoalCard` for the "Completed" state (100% progress, congratulatory text)
- [x] 2.3 Verify and update `GoalCard` `@Preview` functions for both Light and Dark modes

## 3. Screen Layout & Empty State

- [x] 3.1 Update `DashboardScreenContent` to use updated typography and colors for the Empty State
- [x] 3.2 Adjust `LazyColumn` item spacing and padding to match the premium layout aesthetic
- [x] 3.3 Verify and update `DashboardScreenContent` `@Preview` functions for both Light and Dark modes

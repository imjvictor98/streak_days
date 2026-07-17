## 1. Typography & Theme Setup

- [x] 1.1 Download and add font files (Plus Jakarta Sans, Hanken Grotesk, Inter, Be Vietnam Pro) to `app/src/main/res/font`.
- [x] 1.2 Update `Type.kt` or `Theme.kt` to define standard typography tokens mapping to these font families.

## 2. Core UI Components

- [x] 2.1 Create `StreakTopAppBar.kt` in `core/ui/components` with support for title, navigation icon, and action icons. Add `@Preview`.
- [x] 2.2 Create `StreakPrimaryButton.kt` and `StreakOutlineButton.kt` in `core/ui/components` according to Figma specs (border radius, heights, colors). Add `@Preview`.
- [x] 2.3 Create `StreakTextField.kt` in `core/ui/components` with left icon support and specific background styling. Add `@Preview`.
- [x] 2.4 Create `StreakFloatingActionButton.kt` in `core/ui/components` with the specific box shadow and layout. Add `@Preview`.

## 3. Compound Components Refactoring

- [x] 3.1 Refactor `GoalCardScope.kt` to ensure all necessary DSL slots are present for the new layout (Header, ProgressArea, StatsArea, ActionArea).
- [x] 3.2 Refactor `GoalCardScopeImpl.kt` and `GoalCard.kt` to apply the exact Figma tokens (16px radius, specific padding, Hanken Grotesk/Inter fonts). Add `@Preview`.

## 4. Feature Screens Refactoring

- [x] 4.1 Refactor `DashboardScreen.kt` to use `StreakTopAppBar` and `StreakFloatingActionButton`. Use the updated `GoalCard` compound component layout. Update the `@Preview`.
- [x] 4.2 Update `DashboardScreen.kt` preview and verify layout changes against Figma.
- [x] 4.3 Create a new `FormContainer.kt` or directly refactor the layout inside `CreateGoalScreen.kt` (`feature/create/ui`) to match the 48px radius white background card.
- [x] 4.4 Refactor `CreateGoalScreen.kt` to use `StreakTextField`, `StreakPrimaryButton`, and `StreakTopAppBar`.
- [x] 4.5 Update `CreateGoalScreen.kt` preview and verify layout changes against Figma.

## 1. Reusable Components

- [x] 1.1 Create `StreakRoundedTextField` component in `core/ui/components/StreakRoundedTextField.kt`. Ensure it uses `BasicTextField` with pill shape (9999px), `PrimaryContainer` background, leading icon slot, and exact padding (16px left for icon, 48px left for text).
- [x] 1.2 Add `@Preview` for `StreakRoundedTextField` with Light and Dark modes.

## 2. Screen Implementation

- [x] 2.1 Update `CreateGoalScreen.kt` to replace existing `StreakTextField`s with the new `StreakRoundedTextField`.
- [x] 2.2 Add the Hero Graphic (Target/Calendar icon in a circle) at the top of the `CreateGoalScreenContent`.
- [x] 2.3 Style the labels above the inputs with a `16px` left margin so they perfectly align with the text inside the pill input.
- [x] 2.4 Update the primary "Save Goal" button to use `PrimaryVariant` background, full rounding (9999px), and a drop shadow.
- [x] 2.5 Ensure the screen background uses `MaterialTheme.colorScheme.background` and remove all hardcoded hex colors.
- [x] 2.6 Update `@Preview` functions in `CreateGoalScreen.kt` and verify they render the new designs correctly in both Light and Dark modes.

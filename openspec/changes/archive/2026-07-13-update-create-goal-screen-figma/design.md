## Context

The `CreateGoalScreen` is currently built using standard Material 3 components and hardcoded colors. A new Figma design has been provided that introduces a specific visual language for the app, featuring fully rounded inputs (pill-shaped) with no outline, specific background colors (`PrimaryContainer`), and a hero graphic. We need to implement this UI exactly as specified.

## Goals / Non-Goals

**Goals:**
- Update `CreateGoalScreen` to perfectly match the Figma design (node `1:135`).
- Extract a reusable `StreakRoundedTextField` component for the rounded inputs, as this pattern will likely be reused across the app.
- Ensure dark mode support by using `MaterialTheme.colorScheme` instead of hardcoded hex values.

**Non-Goals:**
- Do not change the underlying logic or ViewModel behavior of the screen.
- Do not introduce functional changes (like the duration chips or date picker dialog) at this stage; focus purely on styling the current data fields to match the visual language.

## Decisions

- **Reusable Component (`StreakRoundedTextField`)**: To achieve the exact pill shape, transparent borders, and specific padding without fighting Material 3 defaults, we will create a custom composable utilizing `BasicTextField`.
- **Theme Usage**: 
  - `PrimaryContainer` (`#EFF4FF`) for the input backgrounds.
  - `PrimaryVariant` (`#4648D4`) for the main Create Goal button.
  - `OnSurfaceVariant` for the labels above the inputs.

## Risks / Trade-offs

- [Risk] Custom text fields (using `BasicTextField`) require manually handling focus states, cursor colors, and accessibility. → Mitigation: We will test the new `StreakRoundedTextField` thoroughly to ensure it feels native and accessible.

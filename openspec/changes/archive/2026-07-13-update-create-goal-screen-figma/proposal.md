## Why

The current `CreateGoalScreen` uses a functional, non-styled form layout that does not match the latest Figma design language. To provide a premium user experience and maintain consistency with the new design system, we need to update the screen's visual style, including rounded inputs, specific padding/margins, custom icons, and proper theme color mappings.

## What Changes

- Update `CreateGoalScreen` layout to match the provided Figma specifications (fully rounded inputs, 16px label indent, no outline).
- Map hardcoded colors to the new `StreakDaysTheme` semantic tokens (e.g., `#EFF4FF` to `PrimaryContainer`, `#4648D4` to `PrimaryVariant`).
- Add a hero graphic at the top of the form with a circular blue background and icon.
- Replace standard text fields with custom-styled text fields that include leading icons (Goal Name, Duration, Start Date).
- Implement the "Create Goal" primary button with a drop shadow and full rounding.

## Capabilities

### New Capabilities
- `create-goal-ui`: Visual requirements and specifications for the Create Goal screen.

### Modified Capabilities

## Impact

- **Affected Modules:** `app` module, specifically `CreateGoalScreen.kt` and potentially reusable UI components in `core/ui/components` if we create a new `StreakRoundedTextField`.
- **UI:** The `CreateGoalScreen` will look completely different, strictly adhering to the Figma design.
- **Dependencies/SDK:** No impact on minimum SDK or new dependencies. No new permissions required.
- **Best Practices:** All updated Composable screens will maintain their `@Preview` functions.

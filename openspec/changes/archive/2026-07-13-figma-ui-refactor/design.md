## Context

The current user interface for Streak Days was initially built without a rigorous mapping to the updated Figma design system tokens. With the new Figma layouts ("Home - Streak Days" and "Novo Objetivo"), we have clear typography rules, updated color palettes, and specific spacing and border radius definitions (e.g., 48px radius for form containers). Furthermore, we need to strictly enforce the Compound Components pattern defined in our OpenSpec `compose-guidelines`, ensuring reusable UI elements are decoupled from feature-specific data models.

## Goals / Non-Goals

**Goals:**
- Extract reusable components to `core/ui/components` (e.g., `StreakTopAppBar`, `StreakPrimaryButton`, `StreakOutlineButton`, `StreakTextField`, `StreakFloatingActionButton`).
- Define the new typography tokens in the application `Theme.kt` or `Type.kt` (Plus Jakarta Sans, Hanken Grotesk, Inter, Be Vietnam Pro).
- Refactor the `GoalCard` to fully utilize the `GoalCardScope` DSL, abstracting layout rules (16px radius, padding, colors) into `GoalCardScopeImpl`.
- Update `DashboardScreen` and `CreateGoalScreen` to use the new atomic components and layouts.

**Non-Goals:**
- Changes to navigation logic or ViewModel architecture.
- Feature additions or backend logic modifications.
- Refactoring screens other than Dashboard and Create in this initial pass.

## Decisions

- **Typography Mapping**: We will introduce four new font families to the Compose Typography system to match the design exactly. Custom text styles will be mapped to standard Material 3 typography roles where appropriate, but heavily customized in our `Theme.kt`.
- **Compound Components**: For complex items like `GoalCard`, we will use the Scope pattern. The `DashboardScreen` will provide the data (e.g., "Current 3", "Target 30") while the `GoalCardScopeImpl` determines exactly how the Hanken Grotesk and Inter fonts are applied visually.
- **Form Containers**: The "Create Goal" screen uses a distinct 48px radius white background card. This will be implemented as a specialized `FormContainer` or directly as a Column modifier in the feature UI, depending on its reusability (we will implement it directly in the feature UI for now, as it seems specific to transactional screens).

## Risks / Trade-offs

- **Risk**: Adding four different custom font families might increase the APK size slightly. 
  - *Mitigation*: Ensure we only include the font weights that are actually used (Regular, Medium, SemiBold, Bold) in WOFF2 or TTF format.
- **Trade-off**: The strict Compound Components approach increases the number of files and boilerplate (Scope interface, Scope implementation, Composable wrapper) per component.
  - *Rationale*: The benefit of reusability and clean separation of concerns heavily outweighs the cost of the initial boilerplate, especially for a premium UI.

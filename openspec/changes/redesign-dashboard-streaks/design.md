## Context

The current `DashboardScreen` uses standard Material 3 Cards to display basic text properties of each `Goal`. While functional, the presentation lacks visual impact. The user has proposed a UI/UX update based on a premium Dribbble design reference, which typically features elements such as circular progress rings, elevated cards, distinct typography, and subtle micro-interactions or shadows. We will implement these modern design paradigms using Jetpack Compose.

## Goals / Non-Goals

**Goals:**
- Completely redesign `GoalCard` to visually represent the current and longest streaks.
- Add a circular progress indicator or similar visual gauge to show progression toward the `targetDurationDays`.
- Enhance typography, spacing, and colors to give the dashboard a premium feel.
- Maintain existing `@Preview` setups and ensure the new components support both Light and Dark modes.

**Non-Goals:**
- Changing the underlying Room database schema, ViewModels, or Domain Models (`Goal`).
- Adding complex animation libraries or third-party charting libraries (unless built-in Compose Canvas or ProgressIndicators prove insufficient, which is unlikely).

## Decisions

**1. Custom Visual Indicators vs Third-Party Libraries:**
*Decision:* We will use Jetpack Compose's native `Canvas` API and `CircularProgressIndicator` to create the visual streak representation.
*Rationale:* Relying on native Compose capabilities keeps the app size small, maintains performance, avoids external dependency risks, and provides complete customization over the styling to match the reference.

**2. Component Extraction:**
*Decision:* We will break down the new `GoalCard` into smaller sub-composables (e.g., `StreakProgressRing`, `StreakStatsText`) within `DashboardScreen.kt` or its own file if it grows too large.
*Rationale:* This improves maintainability and allows us to create isolated `@Preview` functions for each part of the complex new card UI.

## Risks / Trade-offs

- **Risk: Increased layout complexity affecting scroll performance in LazyColumn.**
  *Mitigation:* Keep the Composable hierarchy as flat as possible. Use `remember` for complex calculations (like sweep angles for the circular progress) to avoid unnecessary recomposition.
- **Risk: Accessibility (a11y) degradation.**
  *Mitigation:* Ensure that the new visual elements (like the progress ring) are properly annotated with `semantics` and `contentDescription` so screen readers can accurately interpret the visual data.

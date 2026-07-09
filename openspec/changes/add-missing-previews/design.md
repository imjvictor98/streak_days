## Context

The application currently has several Jetpack Compose screens (`DashboardScreen`, `CreateGoalScreen`, `GoalDetailsScreen`) but zero `@Preview` functions. Previews are essential for rapid UI iteration, allowing developers to see UI changes instantly without deploying to an emulator or physical device.

## Goals / Non-Goals

**Goals:**
- Provide Jetpack Compose `@Preview` functions for all existing screens and reusable components.
- Ensure previews accurately reflect the app's theming by wrapping them in `StreakDaysTheme`.
- Include previews for different states (e.g., Light and Dark mode) where beneficial.

**Non-Goals:**
- Introducing new UI testing frameworks like Paparazzi or Roborazzi (only standard Compose Previews for now).
- Refactoring the UI code significantly (only adding preview functions).

## Decisions

- **Preview Wrapping**: All previews will be wrapped in `StreakDaysTheme { ... }` so they use the correct typography, colors, and shapes.
- **Mock Data**: We will create simple mock data within the preview functions or use default parameters to satisfy the required UI state inputs. For screens taking a ViewModel, we will ensure there is a stateless version of the composable that accepts raw state or lambda callbacks, allowing it to be previewed easily. (Wait, checking the code, if they take ViewModels, we might need to preview the inner content or pass mock state).
- **Multiple Previews**: Where appropriate, we will add standard `@Preview` annotations to test both Light Mode and Dark Mode configurations.

## Risks / Trade-offs

- **Risk**: Screens strongly coupled to ViewModels may be difficult to preview.
  → **Mitigation**: Extract state-hoisted (stateless) composables that accept state objects instead of ViewModels, or provide mock/fake ViewModels if necessary.
- **Risk**: Boilerplate in creating mock data for complex screens.
  → **Mitigation**: Create simple helper functions or predefined mock objects within the `ui/theme` or preview packages to reduce duplication.

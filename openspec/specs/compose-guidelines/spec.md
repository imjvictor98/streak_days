# Compose Guidelines

## Purpose
Defines the architectural and design patterns for building UI components with Jetpack Compose in this project.

## Requirements

### Requirement: Compound Components Pattern
Custom UI components that act as containers or have configurable sub-elements (like Cards, Dialogs, or complex List Items) SHALL use the Compound Components pattern via a Scope interface.

#### Scenario: Creating a configurable container component
- **WHEN** a developer creates a new container component (e.g., `StreakCard`) that can contain specific sub-elements like a title and an action button
- **THEN** they must define a `StreakCardScope` interface specifying the allowed composables (e.g., `@Composable fun Title(...)`, `@Composable fun ActionButton(...)`).
- **AND** the `StreakCard` composable must accept a receiver function parameter: `content: @Composable StreakCardScope.() -> Unit`.

### Requirement: Separation of Interface and Implementation
Interfaces and their concrete implementations SHALL be placed in separate files within the same package.

#### Scenario: File Structure for a Compound Component
- **WHEN** building a component using the Compound Components pattern
- **THEN** the scope interface (e.g., `StreakCardScope`) must be in its own file `StreakCardScope.kt`.
- **AND** the implementation (e.g., `StreakCardScopeImpl`) must be in a separate file `StreakCardScopeImpl.kt`.
- **AND** the main composable function must be in its own file (e.g., `StreakCard.kt`).

### Requirement: GoalCard Refactoring to Compound Components
The `GoalCard` used in the Dashboard SHALL be refactored to use the Compound Components pattern, strictly separating the domain model from the UI component, and strictly adhering to the Figma design layout.

#### Scenario: Rendering the GoalCard
- **WHEN** the `DashboardScreen` renders a `GoalCard`
- **THEN** it must invoke the `GoalCard` composable and use the `GoalCardScope` DSL block to supply sub-components like `Header` (title and subtitle), `ProgressArea` (ring and percentage), `StatsArea` (current, best, target), and `ActionArea` (log relapse button).
- **AND** the implementation `GoalCardScopeImpl` MUST handle all layout paddings, 16px corner radius, and font assignments (Hanken Grotesk and Inter) as specified in Figma.

### Requirement: Typography System Tokens
The Compose Theme Typography SHALL define specific tokens for the exact font families used in the Figma design.

#### Scenario: Defining Fonts
- **WHEN** the `Theme.kt` is initialized
- **THEN** it must provide mappings for `Plus Jakarta Sans`, `Hanken Grotesk`, `Inter`, and `Be Vietnam Pro`.
- **AND** components must consume these fonts instead of the default system fonts.

### Requirement: Core UI Atomic Components
Reusable atomic components MUST be defined in `core/ui/components` to enforce visual consistency across all feature screens.

#### Scenario: Using Core Buttons and Fields
- **WHEN** a feature screen requires a primary action or a text input
- **THEN** it must use `StreakPrimaryButton`, `StreakOutlineButton`, or `StreakTextField` respectively.
- **AND** these components must encapsulate the Figma design tokens (e.g., 48px/52px heights, specific corner radii, and Be Vietnam Pro/Inter fonts).

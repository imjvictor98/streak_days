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
The `GoalCard` used in the Dashboard SHALL be refactored to use the Compound Components pattern, strictly separating the domain model from the UI component.

#### Scenario: Rendering the GoalCard
- **WHEN** the `DashboardScreen` renders a `GoalCard`
- **THEN** it must invoke the `GoalCard` composable and use the `GoalCardScope` DSL block to supply sub-components like `Header`, `ProgressRing`, `StatsRow`, and `ActionArea` with primitive data or UI states.

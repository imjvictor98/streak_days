## ADDED Requirements

### Requirement: GoalCard Refactoring to Compound Components
The `GoalCard` used in the Dashboard SHALL be refactored to use the Compound Components pattern, strictly separating the domain model from the UI component.

#### Scenario: Rendering the GoalCard
- **WHEN** the `DashboardScreen` renders a `GoalCard`
- **THEN** it must invoke the `GoalCard` composable and use the `GoalCardScope` DSL block to supply sub-components like `Header`, `ProgressRing`, `StatsRow`, and `ActionArea` with primitive data or UI states.

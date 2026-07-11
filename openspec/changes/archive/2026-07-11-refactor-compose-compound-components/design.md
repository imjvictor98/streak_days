## Context

The `DashboardScreen` currently utilizes a monolithic `GoalCard` component. This component takes a `Goal` domain object and internally renders a title, progress ring, statistics text, and a relapse action button. This tight coupling between the UI component and the domain model violates UI-Domain separation and limits the reusability of the `GoalCard`. Following our newly established architectural guidelines (`openspec/specs/compose-guidelines/spec.md`), complex UI containers should utilize the Compound Components pattern via a Scope interface.

## Goals / Non-Goals

**Goals:**
- Refactor `GoalCard` to be a pure UI Compound Component, decoupled from the `Goal` domain object.
- Separate the component interface (`GoalCardScope`) and its concrete implementation (`GoalCardScopeImpl`) into distinct files as per guidelines.
- Enable `DashboardScreen` to construct the card using the scoped DSL block, composing its internal parts manually (e.g., `Header`, `ProgressRing`, `StatsRow`, `ActionArea`).

**Non-Goals:**
- We are not changing any business logic or view models in the Dashboard feature.
- We are not redesigning the visual appearance of the `GoalCard` (the UI will look identical).
- We will not refactor other screens outside of `DashboardScreen` and `GoalDetailsScreen` in this specific change.

## Decisions

- **Decision 1: File Structure Separation**
  - *Rationale*: To adhere to the new `compose-guidelines`, the `GoalCardScope` interface will be housed in `GoalCardScope.kt` and its private implementation `GoalCardScopeImpl` in `GoalCardScopeImpl.kt`. The main composable will stay in `GoalCard.kt`.
- **Decision 2: Passing raw data instead of `Goal` model**
  - *Rationale*: By passing strings and primitive types to the internal composables (e.g., `Header(title = goal.name)`), the `GoalCard` components no longer need to know about the `Goal` entity, increasing reusability.

## Risks / Trade-offs

- **Risk**: Increased boilerplate. Writing the interface and implementation in separate files adds to the file count and verbosity.
  - *Mitigation*: The trade-off is accepted for better strictness and DSL clarity. Once the pattern is established, copying/pasting the structure will become trivial.

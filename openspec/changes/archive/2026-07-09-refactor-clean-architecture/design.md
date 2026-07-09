## Context

The current architecture has layers (`data`, `domain`, `ui`, `di`), but it has some violations of Clean Architecture principles:
- The Domain layer depends on Data layer entities (`EntityGoal`, `EntityRelapse`).
- ViewModels contain Android framework dependencies (`Context`, Glance Widgets).
- ViewModels interact directly with Repositories instead of using Use Cases.

To ensure long-term scalability and testability, we are refactoring to a strict Clean Architecture approach and adopting "package by feature".

## Goals / Non-Goals

**Goals:**
- Decouple the Domain layer from the Data layer and Android framework.
- Standardize the use of Use Cases (Interactors) for business logic.
- Adopt a "package by feature" structure.
- Remove side effects from ViewModels (e.g., Widget updates).

**Non-Goals:**
- Add any new functionality or change user-facing behavior.
- Change the database schema or data layer persistence logic.

## Decisions

- **Package Structure**: We will move from package-by-layer to package-by-feature.
  - *Rationale*: Feature-based packaging scales better as the number of screens and domains grows, keeping related UI, domain, and data components together, except for core components.
- **Domain Models**: We will map Room entities to pure Domain models inside the `GoalRepositoryImpl`.
  - *Rationale*: The Domain layer must be agnostic to the persistence framework.
- **Use Cases**: ViewModels will only inject Use Cases, not Repositories.
  - *Rationale*: Centralizes business logic and prevents ViewModels from becoming god-classes.
- **WidgetUpdater Abstraction**: Create a `WidgetUpdater` interface in the Domain layer and implement it in a `core/widget` module/package.
  - *Rationale*: Removes Android `Context` and Glance dependencies from ViewModels and Domain logic.

## Risks / Trade-offs

- **Risk: Breaking existing imports and causing compilation errors during file moves.**
  - *Mitigation*: We will do the refactoring step-by-step and rely on compiler checks to ensure all imports are updated correctly.
- **Risk: Boilerplate overhead.**
  - *Trade-off*: Introducing Use Cases adds more files and boilerplate. However, the benefit in testability and separation of concerns outweighs the cost.

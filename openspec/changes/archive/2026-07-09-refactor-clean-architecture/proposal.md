## Why

The current architecture has several violations of Clean Architecture principles, such as the Domain layer depending on Data layer entities (e.g., `StreakCalculator` importing Room entities) and UI ViewModels containing Android framework dependencies (e.g., `GoalsViewModel` updating the widget using `Context`). This change aims to refactor the codebase to strictly adhere to Clean Architecture and Clean Code, decoupling the domain from the data layer, introducing Use Cases to encapsulate business logic, and reorganizing the project structure into a "package by feature" approach for better scalability and maintainability.

## What Changes

- Reorganize the package structure from "package by layer" to "package by feature" (e.g., `core`, `feature/dashboard`, `feature/details`, `feature/create`).
- Decouple the Domain layer from the Data layer by mapping Room entities to pure Domain models within the Repository implementation before passing them to Domain services (e.g., `StreakCalculator`).
- Introduce Use Cases (Interactors) to replace direct Repository calls from ViewModels (e.g., `LogRelapseUseCase`, `GetAllGoalsUseCase`).
- Remove `Context` from ViewModels by creating a `WidgetUpdater` abstraction in the Domain layer and implementing it in a core widget package to handle side effects.

## Capabilities

### New Capabilities
- `architecture-refactor`: Standardizing the architecture across the app to use "package by feature", Use Cases, and pure Domain models.

### Modified Capabilities
- (None - The functional requirements of the app remain exactly the same; this is a purely technical refactoring.)

## Impact

- Affects the `app` module structure, requiring significant file movements.
- Affects `GoalsViewModel`, `StreakCalculator`, and `GoalRepositoryImpl`.
- Introduces new Use Case classes in the Domain layer.
- Introduces a new `WidgetUpdater` abstraction and implementation.
- Does not change any user-facing capabilities or features, but improves testability and separation of concerns.

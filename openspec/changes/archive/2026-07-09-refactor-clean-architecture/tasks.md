## 1. Domain Layer Refactoring (Core Models & Interfaces)

- [x] 1.1 Extract pure Domain models into `core/domain` package if not already there, ensuring they have no dependencies on `data/local`.
- [x] 1.2 Update `GoalRepository` interface in `domain` to use and return only pure Domain models.
- [x] 1.3 Create `WidgetUpdater` interface in the `core/domain` package.
- [x] 1.4 Refactor `StreakCalculator.kt` to use pure Domain models (or primitive arguments) instead of `EntityGoal` and `EntityRelapse`.

## 2. Data Layer Refactoring

- [x] 2.1 Update `GoalRepositoryImpl.kt` to map Room entities (`EntityGoal`, `EntityRelapse`) to pure Domain models before returning them or passing them to `StreakCalculator`.

## 3. Use Case Implementation

- [x] 3.1 Create `LogRelapseUseCase.kt` in the appropriate feature domain package.
- [x] 3.2 Create `GetAllGoalsUseCase.kt` and `GetGoalByIdUseCase.kt` in the appropriate feature domain packages.
- [x] 3.3 Create `CreateGoalUseCase.kt` and `DeleteGoalUseCase.kt` in the appropriate feature domain packages.

## 4. UI Layer Refactoring (Removing Side Effects)

- [x] 4.1 Update `GoalsViewModel.kt` to inject and use `LogRelapseUseCase` and `GetAllGoalsUseCase`, removing `Context`, `StreakDaysWidget`, and `GoalRepository` dependencies.
- [x] 4.2 Update `GoalDetailsViewModel.kt` and `CreateGoalViewModel.kt` to use their respective Use Cases instead of `GoalRepository`.

## 5. Infrastructure Layer (Widget)

- [x] 5.1 Create `core/widget/GlanceWidgetUpdater.kt` implementing `WidgetUpdater` and containing the `StreakDaysWidget().updateAll(context)` logic.
- [x] 5.2 Update `di/RepositoryModule.kt` (or create a new module) to provide the `WidgetUpdater` binding.

## 6. Package Restructuring

- [x] 6.1 Move existing classes to the new "package by feature" structure (`feature/dashboard`, `feature/details`, `feature/create`, `core`).
- [x] 6.2 Fix all import statements across the app and ensure the project compiles successfully.

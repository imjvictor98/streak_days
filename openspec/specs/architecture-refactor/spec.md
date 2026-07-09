# Architecture Refactor

## Purpose
TBD - Defines the clean architecture constraints and rules for the codebase.

## Requirements

### Requirement: Package by Feature
The project SHALL follow a package-by-feature directory structure.

#### Scenario: Code Navigation
- **WHEN** a developer is looking for Dashboard related code
- **THEN** they should find its UI and specific Domain logic under `feature/dashboard`

### Requirement: Pure Domain Layer
The Domain layer SHALL NOT depend on Data layer entities or Android framework classes.

#### Scenario: Streak Calculation
- **WHEN** `StreakCalculator` calculates a streak
- **THEN** it must use pure Domain models or Kotlin primitives, not Room entities.

### Requirement: Use Cases for Business Logic
ViewModels SHALL interact with the Domain layer exclusively through Use Cases.

#### Scenario: Logging a Relapse
- **WHEN** the user logs a relapse from the Dashboard
- **THEN** `GoalsViewModel` calls `LogRelapseUseCase` instead of `GoalRepository`.

### Requirement: Abstracted Side Effects
ViewModels SHALL NOT directly trigger widget updates or hold Android `Context` for side effects.

#### Scenario: Widget Update upon Relapse
- **WHEN** a relapse is logged
- **THEN** `LogRelapseUseCase` triggers a `WidgetUpdater` abstraction which handles the Android specific widget update.

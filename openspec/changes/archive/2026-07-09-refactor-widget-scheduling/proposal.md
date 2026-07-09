## Why

The `StreakDaysApplication` class currently contains business logic for calculating the time until midnight and infrastructure logic for scheduling the `WidgetDailyUpdateWorker` via `WorkManager`. This violates the Single Responsibility Principle (SRP) and couples the Application entry point with scheduling details. Moving this logic to dedicated classes adheres to Clean Architecture and makes the time calculation logic 100% testable.

## What Changes

- Extract the time calculation logic into a new pure domain Use Case: `CalculateTimeUntilMidnightUseCase`.
- Extract the `WorkManager` scheduling logic into a new infrastructure class: `WidgetUpdateScheduler`.
- Refactor `StreakDaysApplication` to inject and call `WidgetUpdateScheduler`, keeping it clean and declarative.
- Provide necessary Hilt bindings for the new components.

## Capabilities

### New Capabilities

*(No new capabilities. This is an architectural refactoring.)*

### Modified Capabilities

*(No modified requirements. Behavior remains exactly the same.)*

## Impact

- **Affected Modules:** `app`
- **Affected Code:** `StreakDaysApplication.kt`
- **New Files:** `CalculateTimeUntilMidnightUseCase.kt` (domain layer), `WidgetUpdateScheduler.kt` (infrastructure layer).
- **DI:** Hilt modules will be updated to provide the new scheduler and use case.
- **Testing:** The new Use Case will be unit testable independently of the Android framework.

## Context

Currently, `StreakDaysApplication` contains inline logic to calculate the time remaining until midnight, which it then uses to schedule `WidgetDailyUpdateWorker` via `WorkManager`. Mixing domain logic (time calculation), framework details (`WorkManager`), and application lifecycle (`Application.onCreate`) violates Clean Architecture and makes testing the time calculation difficult.

## Goals / Non-Goals

**Goals:**
- Extract the time calculation logic into a testable pure domain use case.
- Extract the `WorkManager` scheduling logic into an infrastructure component.
- Keep `StreakDaysApplication` thin, serving only as the entry point.

**Non-Goals:**
- Modifying the internal logic of `WidgetDailyUpdateWorker`.
- Changing the actual requirements or behavior of when the widget updates.

## Decisions

1. **CalculateTimeUntilMidnightUseCase (Domain Layer):**
   - We will create a class with an `operator fun invoke(): Long`.
   - It will encapsulate the `Calendar` logic to find milliseconds until the next midnight.
   - This allows the calculation to be unit-tested without Android dependencies.

2. **WidgetUpdateScheduler (Infrastructure/UI Layer):**
   - We will create a class `WidgetUpdateScheduler` (or an interface in domain and implementation in infrastructure, but a concrete class in the widget/infrastructure package is sufficient since the Application only calls it once).
   - It will inject `CalculateTimeUntilMidnightUseCase` and a `Context` (for `WorkManager`).
   - It exposes a `scheduleMidnightUpdate()` method.
   - It encapsulates the `PeriodicWorkRequestBuilder` and `WorkManager.getInstance(context).enqueueUniquePeriodicWork` logic.

3. **Dependency Injection (Hilt):**
   - Since `CalculateTimeUntilMidnightUseCase` has no dependencies (or depends on a Clock interface if we want to go further, but for now `Calendar.getInstance()` inside it is fine), we can just inject it with `@Inject constructor()`.
   - `WidgetUpdateScheduler` gets `@Inject constructor(@ApplicationContext context: Context, useCase: CalculateTimeUntilMidnightUseCase)`.
   - `StreakDaysApplication` receives `WidgetUpdateScheduler` via field injection (`@Inject lateinit var scheduler: WidgetUpdateScheduler`).

## Risks / Trade-offs

- **Risk:** Timezone changes might not be perfectly handled if the worker is statically delayed.
  **Mitigation:** The current implementation already uses a static initial delay. This refactoring does not change the behavior, it only reorganizes the code.

- **Trade-off:** Adding an extra layer of abstraction (Scheduler and UseCase) for a relatively small block of code.
  **Rationale:** The benefits of testability and adherence to Clean Architecture principles in the entry point (`Application` class) outweigh the cost of two additional small classes.

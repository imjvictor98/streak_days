## 1. Domain Layer

- [x] 1.1 Create `CalculateTimeUntilMidnightUseCase.kt` in `domain/usecase/` to encapsulate the time calculation logic.
- [x] 1.2 Add unit tests for `CalculateTimeUntilMidnightUseCase` verifying time differences.

## 2. Infrastructure Layer

- [x] 2.1 Create `WidgetUpdateScheduler.kt` in `ui/widget/`.
- [x] 2.2 Implement `scheduleMidnightUpdate()` in `WidgetUpdateScheduler`, injecting context and `CalculateTimeUntilMidnightUseCase`.

## 3. Application Layer

- [x] 3.1 Remove inline time calculation and `WorkManager` scheduling from `StreakDaysApplication.kt`.
- [x] 3.2 Inject `WidgetUpdateScheduler` into `StreakDaysApplication`.
- [x] 3.3 Call `widgetUpdateScheduler.scheduleMidnightUpdate()` within `StreakDaysApplication.onCreate()`.

## 1. Update Domain Logic

- [x] 1.1 Update `DeleteGoalUseCase` constructor in `app/src/main/java/com/cvj/app/streakdays/feature/details/domain/usecase/DeleteGoalUseCase.kt` to inject `WidgetUpdater`.
- [x] 1.2 Modify the `invoke` function in `DeleteGoalUseCase.kt` to call `widgetUpdater.updateGoalsWidget()` after deleting the goal.
- [x] 1.3 Update the corresponding tests (e.g. `DeleteGoalUseCaseTest.kt` if it exists) to mock and verify `WidgetUpdater`.

## 2. Update Widget UI

- [x] 2.1 Update the empty state text in `StreakDaysWidgetContent` within `app/src/main/java/com/cvj/app/streakdays/feature/widget/streak/presentation/ui/StreakDaysWidget.kt` to "Você não tem nenhum objetivo ainda".
- [x] 2.2 Verify the `StreakDaysWidgetPreview` renders the updated empty state correctly (if a preview exists for the empty state, otherwise verify the code change).

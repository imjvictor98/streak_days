## Context

The StreakDays app has an Android home screen widget built using Glance (`StreakDaysWidget.kt`). The widget reflects the user's main goal and its progress. Currently, the widget is updated correctly when a new goal is created or a relapse is logged because the respective use cases (`CreateGoalUseCase`, `LogRelapseUseCase`) invoke `WidgetUpdater.updateGoalsWidget()`.

However, `DeleteGoalUseCase` does not call `WidgetUpdater.updateGoalsWidget()`. Therefore, when a goal is deleted, the repository is updated, but the widget continues to display the deleted goal until a background update occurs. Furthermore, the empty state text in the widget reads "Nenhum objetivo ativo" which needs to be updated to a more conversational tone: "Você não tem nenhum objetivo ainda".

## Goals / Non-Goals

**Goals:**
- Fix the bug where the widget shows stale data after a goal is deleted.
- Ensure the widget immediately reflects the empty state or the next available goal when the active goal is deleted.
- Update the empty state UI text to "Você não tem nenhum objetivo ainda".

**Non-Goals:**
- Refactoring the entire widget update mechanism.
- Modifying how `getMainGoal()` is computed.
- Adding new UI components to the widget.

## Decisions

1. **Injecting `WidgetUpdater` into `DeleteGoalUseCase`**:
   - `WidgetUpdater` is already provided by Dagger Hilt in `RepositoryModule` and used by other UseCases.
   - Injecting it into `DeleteGoalUseCase` ensures consistency across the app's UseCases that mutate goal state.
   - *Alternative Considered*: Observing changes directly in the `StreakDaysWidgetReceiver` or using WorkManager. *Rationale for Rejection*: Explicit update calls from UseCases are already the established pattern in this project for synchronous user actions.

2. **Hardcoded Text Update (MVP)**:
   - The string "Nenhum objetivo ativo" in `StreakDaysWidget.kt` will be updated to "Você não tem nenhum objetivo ainda".
   - *Alternative Considered*: Moving it to `strings.xml`. *Rationale*: If it's already hardcoded, we will just change the hardcoded string for a quick fix. If it's in resources, we will update `strings.xml`. Based on earlier exploration, it is currently hardcoded in `StreakDaysWidget.kt`.

## Risks / Trade-offs

- **Risk**: Injecting `WidgetUpdater` into `DeleteGoalUseCase` might break existing tests for `DeleteGoalUseCase` if they strictly mock dependencies.
  - **Mitigation**: Update the `DeleteGoalUseCaseTest` (or similar) to provide a mock `WidgetUpdater`.

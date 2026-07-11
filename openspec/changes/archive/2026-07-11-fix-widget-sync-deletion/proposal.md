## Why

Currently, when a goal is deleted using the `DeleteGoalUseCase`, the Glance widget is not explicitly instructed to update its state. This leads to the widget displaying stale information (i.e. the deleted goal) until a periodic update triggers. Additionally, when there are zero active goals, the widget displays "Nenhum objetivo ativo", which is less friendly than the desired "Você não tem nenhum objetivo ainda". We need to fix this to keep the widget synchronized with the local database instantly and to improve the empty state messaging.

## What Changes

- Modify `DeleteGoalUseCase` to depend on `WidgetUpdater`.
- Call `widgetUpdater.updateGoalsWidget()` inside `DeleteGoalUseCase` immediately after a goal is deleted from the repository.
- Update the empty state text in `StreakDaysWidget.kt` from "Nenhum objetivo ativo" to "Você não tem nenhum objetivo ainda" (using string resources if applicable).

## Capabilities

### New Capabilities
None

### Modified Capabilities
- `widget`: Modifying the widget update behavior on goal deletion and changing empty state text.

## Impact

- **Affected Modules**: `app` (specifically the feature modules for details and widget).
- **Affected Files**: `DeleteGoalUseCase.kt`, `StreakDaysWidget.kt`, and possibly their tests.
- **Dependency changes**: `DeleteGoalUseCase` will now require `WidgetUpdater` injection, which is already bound in `RepositoryModule`.

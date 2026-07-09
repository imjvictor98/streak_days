## Why

The current widget implementation is mixed with the rest of the application's codebase. As the application grows and adopts Clean Architecture, the widget (which possesses its own unique lifecycle, presentation rules, and background update mechanisms) needs to be treated as an independent feature. Refactoring the widget into its own isolated feature module (`feature.widget.streak` package/module) will increase cohesion, improve maintainability, and ensure that widget-specific side effects (like the midnight refresh worker) are fully encapsulated and do not leak into the main app.

## What Changes

- Create a new package/module hierarchy for `feature.widget.streak`.
- Move the Glance UI components (`StreakWidget`) to `feature.widget.streak.presentation.ui`.
- Move the AppWidgetProvider/Receiver (`StreakWidgetReceiver`) to `feature.widget.streak.presentation.receiver`.
- Move the midnight update worker to `feature.widget.streak.presentation.worker` (since it acts as an entry point for updating the widget).
- Create widget-specific UseCases (e.g., `GetStreakWidgetDataUseCase`) in `feature.widget.streak.domain` that interact with the core domain.
- Adjust Dependency Injection (Hilt) to provide the necessary dependencies to the new package structure.
- Ensure that the main application code (Home feature) remains completely decoupled from the widget's internal logic.

## Capabilities

### New Capabilities
- `widget-architecture`: Structural reorganization of the widget into a feature module.

### Modified Capabilities
*(None. The existing requirements for the widget and its daily refresh remain the same.)*

## Impact

- **Affected Code**: All existing widget-related files (Glance composables, receivers, workers) will be moved and reorganized.
- **Architecture**: Enforces Clean Architecture principles by isolating the widget as a distinct presentation/feature layer.
- **Testing**: May require updating imports and DI bindings in existing widget tests.
- **Gradle Modules**: This may just be a package restructuring within the `app` module, or extraction into a dedicated `:feature:widget:streak` Gradle module if modularization is actively being pursued.

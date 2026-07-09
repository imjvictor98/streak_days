## Context

The widget functionality (Glance UI, receivers, and midnight refresh worker) is currently mixed with the main application logic or generic core components. As the application is transitioning to a Clean Architecture pattern, treating the widget as a distinct feature module (`feature.widget.streak`) is necessary. This ensures that the widget's unique lifecycle (background updates, out-of-app rendering) and specific side effects (like waking up the device at midnight to refresh its UI) are isolated from the main `Home` feature and other app screens.

## Goals / Non-Goals

**Goals:**
- Consolidate all widget-related classes (Glance composables, `AppWidgetProvider`, `Worker`) into a single `feature.widget.streak` package structure.
- Introduce widget-specific UseCases in the domain layer of this feature (e.g., `GetStreakWidgetDataUseCase`) to separate the widget's presentation needs from the general `StreakRepository`.
- Improve the cohesion of the codebase by encapsulating the midnight refresh worker exclusively within the widget feature.

**Non-Goals:**
- Changing the visual design or existing functionality of the widget.
- Modifying how streaks are calculated globally (the core domain remains untouched).
- Creating new widgets (e.g., calendar or stats widgets) at this time, though the structure will support them in the future.

## Decisions

1. **Package Structure: `feature.widget.streak`**
   - *Rationale*: Prepares the app for future widgets (e.g., `feature.widget.stats`) by nesting them under `widget`. `streak` specifies the exact scope.
   
2. **Encapsulating the Midnight Worker in `presentation.worker`**
   - *Rationale*: The midnight worker exists solely to trigger a UI refresh of the widget when the day changes. Since it acts as a trigger for presentation, it belongs in the presentation/worker layer of the widget feature. This keeps the rest of the app completely oblivious to this background task.

3. **Widget-Specific UseCases**
   - *Rationale*: The widget may need data formatted differently than the Home screen (e.g., shorter strings or specific state objects like `WidgetUiState`). Providing a `GetStreakWidgetDataUseCase` ensures the Glance UI remains as "dumb" as possible, receiving ready-to-display data.

## Risks / Trade-offs

- **Risk: Dependency Injection Complexity** 
  - *Trade-off*: Injecting UseCases into a `GlanceAppWidgetReceiver` or a `Worker` requires specific Hilt setups (like `@HiltWorker` or `@EntryPoint`). 
  - *Mitigation*: We will carefully update the Hilt bindings and ensure the `WorkerFactory` is correctly configured if not already.

- **Risk: Disconnected Updates**
  - *Trade-off*: By separating the widget, there's a minor risk that main app interactions fail to notify the widget if not wired correctly.
  - *Mitigation*: Ensure that the core `StreakRepository` exposes a `Flow` that the widget's UseCases can observe, or that the main app's UseCases explicitly trigger a widget update via a shared mechanism.

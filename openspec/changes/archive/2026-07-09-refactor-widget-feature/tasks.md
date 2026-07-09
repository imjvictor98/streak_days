## 1. Preparation & Structure

- [x] 1.1 Create the new package structure: `feature.widget.streak.domain` and `feature.widget.streak.presentation` (with `ui`, `receiver`, and `worker` sub-packages).

## 2. Domain Layer

- [x] 2.1 Create `GetStreakWidgetDataUseCase` in `feature.widget.streak.domain` to handle fetching and formatting widget-specific state.
- [x] 2.2 Add or update Hilt dependency injection bindings for the new domain UseCases.
- [x] 2.3 Write or migrate unit tests for `GetStreakWidgetDataUseCase`.

## 3. Worker Migration

- [x] 3.1 Move the existing midnight refresh worker to `feature.widget.streak.presentation.worker`.
- [x] 3.2 Refactor the worker to utilize the new widget-specific UseCases if applicable, ensuring it remains isolated from the main app.
- [x] 3.3 Verify Hilt `@HiltWorker` injection is correctly configured for the new package location.
- [x] 3.4 Fix any imports in the worker and its tests.

## 4. Presentation (Glance UI & Receivers)

- [x] 4.1 Move the Glance composables and state files to `feature.widget.streak.presentation.ui`.
- [x] 4.2 Move the `AppWidgetProvider` (e.g., `StreakWidgetReceiver`) to `feature.widget.streak.presentation.receiver`.
- [x] 4.3 Update `AndroidManifest.xml` to point to the new fully-qualified class name of the widget receiver.
- [x] 4.4 Fix any broken imports in the moved UI and receiver files.

## 5. Verification & Cleanup

- [x] 5.1 Run project linting (ktlint/detekt) to ensure no architectural boundary violations.
- [x] 5.2 Execute all unit and UI tests to confirm that the refactor did not break existing functionality.
- [x] 5.3 Manually verify the widget renders correctly and updates at midnight (via test override or manual trigger).

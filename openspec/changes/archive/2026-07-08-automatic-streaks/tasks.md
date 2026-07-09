## 1. Data Layer

- [x] 1.1 Update `Streak` Room entity (or equivalent data model) to ensure `startDate` (Long timestamp or Date) is stored.
- [x] 1.2 Implement any necessary Room database migrations and update DAO queries.
- [x] 1.3 Add unit tests for the DAO and local data source to verify the `startDate` is persisted properly.

## 2. Domain Layer

- [x] 2.1 Create or update a UseCase (e.g., `CalculateStreakUseCase`) to compute the dynamic streak (difference in calendar days from `startDate` to today, starting at 1).
- [x] 2.2 Add unit tests for the streak calculation logic covering same day, next day, and a week later scenarios.

## 3. UI Layer

- [x] 3.1 Update the relevant `ViewModel` to use the dynamic streak calculation and expose the current streak via `StateFlow`.
- [x] 3.2 Update the Jetpack Compose screens to display the derived streak value seamlessly.
- [x] 3.3 Ensure the Composable screens have updated `@Preview` functions representing the new streak state.
- [x] 3.4 Add or update UI tests (Espresso/Compose UI Test) to verify the correct streak is displayed.

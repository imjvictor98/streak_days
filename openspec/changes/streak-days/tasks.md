## 1. Project Setup

- [x] 1.1 Initialize Android project with Compose, Hilt, and Room dependencies in `build.gradle.kts`
- [x] 1.2 Enable coreLibraryDesugaring in `app/build.gradle.kts` for `java.time` APIs
- [x] 1.3 Setup Hilt application class and update `AndroidManifest.xml`
- [x] 1.4 Setup base theme and typography for Jetpack Compose

## 2. Data Layer

- [x] 2.1 Create `Goal` and `Relapse` entity classes in `data/local/entity/`
- [x] 2.2 Create `StreakDatabase`, `GoalDao`, and `RelapseDao` interfaces in `data/local/`
- [x] 2.3 Implement dependency injection module `DatabaseModule` for Room database
- [x] 2.4 Write unit tests for `GoalDao` and `RelapseDao` in `androidTest/`

## 3. Domain Layer

- [x] 3.1 Create `Streak` domain model and mapping logic from `Goal` + `Relapse` entities
- [x] 3.2 Implement `StreakCalculator` utility using `java.time.LocalDate` and `Instant`
- [x] 3.3 Create `GoalRepository` interface and implementation in `domain/` and `data/repository/`
- [x] 3.4 Write unit tests for `StreakCalculator` using JUnit5

## 4. UI Layer - Goal Management

- [x] 4.1 Create `GoalsViewModel` to manage the list of active goals
- [x] 4.2 Create `DashboardScreen` Compose UI to display the list of active goals and their current streaks
- [x] 4.3 Create `CreateGoalViewModel` to handle new goal input
- [x] 4.4 Create `CreateGoalScreen` Compose UI with name, duration, and start date inputs
- [x] 4.5 Set up Jetpack Navigation to route between Dashboard and Create Goal screens

## 5. UI Layer - Streak Tracking & History

- [x] 5.1 Create `GoalDetailsViewModel` to manage a specific goal's relapse logging and history
- [x] 5.2 Create `GoalDetailsScreen` Compose UI to display longest streak, past streaks, and log relapses
- [x] 5.3 Integrate relapse logging action into `DashboardScreen` and `GoalDetailsScreen`
- [x] 5.4 Update navigation to include `GoalDetailsScreen`

## 6. Polish and Verification

- [x] 6.1 Add UI tests for `DashboardScreen` and `GoalDetailsScreen` using Compose UI Test
- [x] 6.2 Ensure completed goals are visually distinguished on the `DashboardScreen`
- [x] 6.3 Verify behavior on configuration changes (rotation) for all screens

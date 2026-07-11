## 1. Core Domain Refactoring

- [x] 1.1 Remove business logic comments from `app/src/main/java/com/cvj/app/streakdays/core/domain/model/GoalWeeklyProgress.kt` and rename variables if necessary.
- [x] 1.2 Remove explanatory comments from `app/src/main/java/com/cvj/app/streakdays/core/domain/model/Goal.kt`.

## 2. UI & Widget Refactoring

- [x] 2.1 Remove MVP/technical debt comments from `app/src/main/java/com/cvj/app/streakdays/feature/widget/streak/presentation/ui/StreakDaysWidget.kt`.
- [x] 2.2 Check other files in `feature/widget` and `core/designsystem` (like `Theme.kt`, `Type.kt`) and remove redundant comments.

## 3. General Cleanup & Verification

- [x] 3.1 Perform a global search for `//` and `/**` in `app/src/main` to find and delete any remaining implementation comments.
- [x] 3.2 Run all unit tests (`GoalWeeklyProgressTest.kt`, `StreakCalculatorTest.kt`, etc.) to guarantee no logic was accidentally altered during refactoring.

## Context

StreakDays is a new Android application designed to track the number of consecutive calendar days a user has avoided a specific action (a "relapse"). Unlike typical habit trackers that focus on daily tasks, this app tracks streaks of abstinence. The core data includes goals, streaks, and relapse events. The app needs to calculate streaks based on calendar days in the local timezone and persist this data locally. It will use modern Android development practices (MVVM, Jetpack Compose, Room, Hilt, Coroutines).

## Goals / Non-Goals

**Goals:**
- Provide a robust local database schema for Goals and Relapses.
- Accurately calculate streak days based on calendar dates (midnight-to-midnight) rather than rolling 24-hour windows.
- Handle timezone changes and daylight saving time correctly.
- Support multiple concurrent goals.

**Non-Goals:**
- Social/sharing features.
- Cloud sync/backup (local persistence only for v1).
- Multiple relapse categories or severity levels (binary relapse only for v1).
- Backend dependencies.

## Decisions

- **Architecture:** MVVM with Clean Architecture principles. Jetpack Compose for UI. This provides a reactive UI tied closely to the data state.
- **Persistence:** Room Database. We will have entities for `Goal` and `Relapse`. 
  - *Decision*: We will persist `Relapse` events explicitly. A `Goal` will have a `startDate`. Streaks are dynamically calculated intervals between the `startDate` and `Relapse` dates, or between consecutive `Relapse` dates. This avoids state inconsistencies that could occur if we manually incremented a counter.
- **Time/Date Handling:** Use `java.time.LocalDate` and `java.time.Instant` (via desugaring if minSdk < 26). Storing exact timestamps for relapses and `LocalDate` for the goal start date. Streak duration is calculated by comparing these dates using the user's current timezone.

## Risks / Trade-offs

- **Risk: Timezone changes skewing streaks** → Mitigation: By storing absolute events (timestamps) and evaluating them against the current `LocalDate` in the user's active timezone, the app will correctly assess calendar day progression.
- **Risk: Core Library Desugaring Overhead** → Mitigation: Enable `coreLibraryDesugaring` in Gradle to safely use `java.time` APIs on older Android versions without significant performance hits.

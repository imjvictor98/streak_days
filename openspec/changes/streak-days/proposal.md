## Why

Users need a way to track consecutive days toward a personal goal, specifically focused on avoiding a certain action or event. While many habit trackers focus on doing something daily, this app focuses on *not* doing something, similar to a sobriety or abstinence streak. This provides users with a clear visual representation of their progress and history, motivating them to maintain their streaks.

## What Changes

- Create a new Android project.
- Implement goal creation with name, target duration, and start date.
- Implement streak counting logic based on calendar days (midnight-to-midnight in local timezone).
- Implement the ability to log "relapse" events to reset the current streak.
- Implement history tracking for past streaks associated with a goal.
- Implement goal completion state when target duration is reached, with the option to continue tracking.
- Implement support for multiple simultaneous goals.
- Implement local data persistence.

## Capabilities

### New Capabilities
- `goal-management`: Creating and managing multiple goals with target durations.
- `streak-tracking`: Calculating consecutive days, handling relapses, timezone changes, and managing streak history.
- `local-storage`: Persisting goals, streaks, and relapse events locally (e.g., using Room).

### Modified Capabilities

## Impact

- **Gradle Modules**: This is a new project; it will establish the initial `app` module and foundational architecture (MVVM, Jetpack Compose, Room, Hilt).
- **SDK Versions**: Minimum SDK will be 24 (or as appropriate for modern Jetpack Compose), Target SDK 34.
- **Permissions**: No special permissions required for core functionality (notifications optional for v1).

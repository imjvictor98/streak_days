## Why

The current implementation lacks an automatic progression of streak days based on time. By making streaks automatic, a user's streak will naturally increase as time passes without a relapse, creating a more engaging and encouraging experience. For example, starting today grants a streak of 1, and it increments successively each day automatically.

## What Changes

- Implement streak calculation to be time-based (current date minus the start date or last relapse date).
- Update the UI to display the automatically calculated streak.
- Store the start date to accurately compute the current streak.

## Capabilities

### New Capabilities
- `automatic-streaks`: Core logic for calculating the current streak based on start date and elapsed time.

### Modified Capabilities

## Impact

- **Data Model**: Need to ensure `startDate` or similar timestamp is stored for a streak.
- **UI**: The main screen will need to be updated to show the computed streak in real-time or daily updates.
- **Gradle Modules**: Affects the `app` module where the UI and logic reside. No new permissions are expected.

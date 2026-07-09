## Context

Users need an automatic progression of their streak days to maintain motivation. Rather than manually incrementing a streak day-by-day, the app should naturally track time and display an increasing streak count as long as no relapse occurs.

## Goals / Non-Goals

**Goals:**
- Automatically calculate the streak count based on the time elapsed since the start date or the most recent relapse.
- Update the main UI to instantly reflect the correct, calculated streak count when the app is opened or resumed.
- Ensure the start date is properly persisted to support this calculation.

**Non-Goals:**
- Modifying how relapses are logged or categorized.
- Introducing complex timezone-aware edge case handling (initially, standard UTC/local day differences will suffice).

## Decisions

**Dynamic Time-based Calculation:**
- The app will compute the streak "on-the-fly" (`currentDate - startDate` in days, plus 1 as requested) when rendering the UI, rather than using a background worker (like WorkManager) to update a database integer every midnight.
- *Rationale*: Background jobs can be unreliable on Android due to battery optimizations. Dynamic calculation guarantees the user always sees the correct number when opening the app.

**Start Date Persistence:**
- The core data model (likely a Room Entity) must ensure it holds a `startDate` timestamp. When a relapse occurs, this `startDate` is reset to the time of the relapse to restart the calculation.

## Risks / Trade-offs

- **Risk: Timezone changes** → Users traveling across timezones might see their streak skip or jump unexpectedly. *Mitigation*: Rely on UTC timestamps for storing the start date and compare against the user's local timezone when deriving the calendar days difference.

## Why
We now have a well-elaborated README and OpenSpec to document our architecture, design, and business rules. Maintaining comments in the code adds noise, risks becoming outdated, and violates Clean Code principles which advocate for self-documenting code through expressive naming.

## What Changes
- Adopt a strict "Zero Comments Policy" across the codebase.
- "What/How" comments will be removed; the code will be refactored to be self-explanatory (extracting methods, better variable naming).
- "Why" comments (business rules and edge cases) will be removed from the code and migrated to their respective OpenSpec specifications.
- "TODOs" and technical debt comments will be removed and logged into a technical debt spec or tasks list.

## Capabilities

### New Capabilities
- `code-standards`: Define the Zero Comments Policy and the traceability approach (how the code points to OpenSpec for complex rules).

### Modified Capabilities
- `automatic-streaks`: Migrate business rules currently hidden in code comments (e.g., handling 0 streak days) into this spec.
- `widget`: Migrate technical debt/MVP notes into the spec or design docs.

## Impact
- **Codebase:** Widespread deletion of comments across `app` module Kotlin files.
- **Readability:** Code will become purely semantic, heavily relying on Domain-Driven Design (DDD) naming conventions.
- **Process:** Future development will require business rules to be explicitly documented in OpenSpec rather than explained via comments.

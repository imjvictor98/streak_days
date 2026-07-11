## Context

The codebase currently contains comments explaining "what" the code does (redundant with clean code naming), "why" certain business rules exist (which should be in OpenSpec), and "TODOs" (technical debt). This initiative enforces a strict Zero Comments Policy to rely entirely on code expressiveness and OpenSpec documentation.

## Goals / Non-Goals

**Goals:**
- Remove all `//` and `/**` comments that explain implementation details.
- Refactor variable/function names to replace the need for "what/how" comments (Domain-Driven Design).
- Migrate business rules currently stored in code comments to the OpenSpec `specs/` directory.

**Non-Goals:**
- Removing standard file headers (like copyright) if they exist.
- Major refactoring beyond what's necessary to remove comments.

## Decisions

1. **Zero Comments Policy:** No comments will be allowed in Kotlin files. Code must be purely semantic.
   - *Rationale:* Comments rot and become out of sync. Clean naming and external specs are more resilient.
2. **OpenSpec Traceability:** Complex business rules (like streak logic handling 0-day edge cases) are fully documented in `specs/automatic-streaks/spec.md`. The code will rely on domain-specific naming rather than comments.
   - *Rationale:* Keeps the codebase clean while maintaining a single source of truth for business requirements.

## Risks / Trade-offs

- [Risk] Developers may struggle to understand complex legacy logic without inline comments.
  → Mitigation: The OpenSpec repository must be kept perfectly in sync with the codebase, and developers must consult it when domain names aren't enough.
- [Risk] Loss of context for technical debt (TODOs).
  → Mitigation: Create explicit tasks or issues for any TODOs removed from the code.

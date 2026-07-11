## ADDED Requirements

### Requirement: Code Comments Standard
The system SHALL strictly follow a Zero Comments Policy for application code.

#### Scenario: Explaining implementation
- **WHEN** code requires explanation of how it works
- **THEN** developers MUST refactor using Domain-Driven Design (extract methods, rename variables) instead of adding comments

#### Scenario: Explaining business rules
- **WHEN** a business rule is complex or non-obvious
- **THEN** developers MUST document the rule in OpenSpec and use expressive naming in the code, rather than inline comments

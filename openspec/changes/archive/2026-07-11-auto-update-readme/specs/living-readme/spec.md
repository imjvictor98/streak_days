## ADDED Requirements

### Requirement: Agent auto-generates README on archive
The agent SHALL read the consolidated specs in `openspec/specs/` and regenerate the project's root `README.md` when completing the opsx-archive workflow.

#### Scenario: Archiving a completed change
- **WHEN** the user invokes `/opsx-archive` to finalize a change
- **THEN** the agent finalizes the OpenSpec archival process
- **THEN** the agent reads the updated files in `openspec/specs/`
- **THEN** the agent rewrites `README.md` with an updated summary of the app's features and architecture

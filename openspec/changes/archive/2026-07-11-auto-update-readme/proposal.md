## Why

The `README.md` of the project quickly becomes outdated as new features are added and merged. To solve this, we want to implement a "Living Documentation" approach where the README is automatically updated based on the actual OpenSpec specs every time a change is finalized via the `/opsx-archive` workflow. This ensures the documentation is always perfectly in sync with the current state of the app.

## What Changes

- Modify the agent workflow `.agent/workflows/opsx-archive.md` to include a final step.
- The new step will instruct the agent to read the consolidated files in `openspec/specs/`.
- The agent will then generate and overwrite the project's root `README.md` with an updated summary of the app's features, architecture, and current state.

## Capabilities

### New Capabilities
- `living-readme`: Defines the process of automatically updating the project's README.md from the OpenSpec specs during the change archival workflow.

### Modified Capabilities
- (None)

## Impact

- **.agent/workflows/opsx-archive.md**: Will receive new instructions at the end of the workflow.
- **README.md**: Will become an auto-generated file, constantly rewritten by the agent on archive.

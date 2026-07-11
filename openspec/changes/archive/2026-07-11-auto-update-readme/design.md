## Context

The `README.md` file at the root of the project gets outdated easily. Since we are using OpenSpec to manage features and their requirements, we have a pristine, single source of truth in `openspec/specs/`. When a feature is completed, `/opsx-archive` merges delta specs into the main specs. This is the perfect moment to automatically regenerate the README.

## Goals / Non-Goals

**Goals:**
- Automate the generation of `README.md` at the end of the `/opsx-archive` workflow.
- Ensure the README always reflects the exact state of the `openspec/specs/` directory.

**Non-Goals:**
- We are not implementing this as a GitHub Action CI/CD pipeline. This is entirely managed locally by the AI agent during the workflow execution.

## Decisions

- **Workflow Hook**: We will append a final instruction step to `.agent/workflows/opsx-archive.md`. 
  - *Rationale*: The workflow already performs the `openspec archive` command, making it the right place to read the updated specs and rewrite the README. This relies on the agent's natural language capabilities to summarize the specs elegantly.
- **Prompt Structure**: We will instruct the agent in the workflow to use a specific structure for the README (e.g., App Name/Pitch, Main Features based on specs, Tech Stack) to maintain consistency.

## Risks / Trade-offs

- **Risk**: The agent might hallucinate or format the README poorly since it relies on LLM summarization.
  - *Mitigation*: The instructions in the workflow will explicitly outline the required sections of the README to ensure a predictable and high-quality output.

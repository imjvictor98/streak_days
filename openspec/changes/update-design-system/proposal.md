## Why

We need to update the application's design system to match the new visual identity defined in Figma. This will provide a more modern look, better typography structure, and align the application with the latest design guidelines.

## What Changes

- Replace default Material 3 color palette with the new custom color scheme (whites, custom indigo primary, dark blue texts).
- Import `Plus Jakarta Sans` and `Hanken Grotesk` fonts.
- Map the typography to use `Plus Jakarta Sans` for main headers and `Hanken Grotesk` for all other texts, simplifying the 4-font Figma design down to 2 for better performance and maintainability.
- Update `Color.kt` and `Type.kt` in the `core/designsystem/theme` package.

## Capabilities

### New Capabilities
- `design-system`: Establish the new color palette and typography rules.

### Modified Capabilities


## Impact

- **Modules**: `app` module (specifically `core/designsystem/theme`).
- **Dependencies**: Font files will be added directly to `res/font` for offline support and stability.
- **UI**: All existing screens will automatically inherit the new typography and colors if they use the MaterialTheme properly.

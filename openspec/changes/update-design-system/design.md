## Context

The application currently relies on the default Material 3 templates for its design system, particularly for typography and colors. The new Figma designs require a specific, modern visual identity. 

## Goals / Non-Goals

**Goals:**
- Replace the default Material 3 color definitions with custom colors (Primary: `#4D44E3`, Background: `#F7F9FB`, etc.).
- Introduce custom fonts `Plus Jakarta Sans` and `Hanken Grotesk` from Google Fonts.
- Update `Typography` definitions to map `display`/`headline`/`title` to `Plus Jakarta Sans` and `body`/`label` to `Hanken Grotesk`.

**Non-Goals:**
- Modifying spacing or shape systems unless explicitly required by the theme.
- Redesigning components that already correctly use `MaterialTheme` parameters.

## Decisions

- **Typography Simplification**: Instead of importing the 4 fonts present in the Figma (`Plus Jakarta Sans`, `Hanken Grotesk`, `Inter`, `Be Vietnam Pro`), we've decided to simplify to just 2 (`Plus Jakarta Sans` for headers, `Hanken Grotesk` for body and labels). This decision was made to reduce complexity in `Type.kt` and keep the app bundle size and rendering performance optimal, while retaining the intended aesthetics.
- **Font Loading**: Fonts will be downloaded as `.ttf` files and placed in `app/src/main/res/font` rather than dynamically loaded via Downloadable Fonts. This ensures offline availability, eliminates network dependency for the UI, and avoids rendering flashes.

## Risks / Trade-offs

- **Risk**: Existing screens might have hardcoded text styles or colors that will not pick up the new theme automatically.
  - **Mitigation**: Once applied, a manual verification of screens (or Compose Previews) is necessary to ensure `MaterialTheme.typography` and `MaterialTheme.colorScheme` are correctly applied across the app.

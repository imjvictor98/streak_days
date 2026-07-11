## Context

The StreakDays Android app currently uses the default launcher icon generated at project creation time. A new, professionally designed icon set (`iconikai-icon-pack`) has been delivered, containing rasterized `ic_launcher.png` files for all standard Android mipmap densities and a Play Store icon. This is a pure asset-replacement change — no code logic is involved.

## Goals / Non-Goals

**Goals:**
- Replace the existing `ic_launcher.png` files in all five mipmap density folders with the new assets.
- Copy the `playstore-icon.png` to an accessible project location for store listing use.
- Ensure the icon name (`ic_launcher`) is preserved so `AndroidManifest.xml` requires no changes.

**Non-Goals:**
- Adding adaptive icon support (`ic_launcher_foreground` / `ic_launcher_background` XML-based adaptive icons) — the new pack provides only flat rasterized icons.
- Updating the iOS icon (out of scope for this change, addressed separately).
- Modifying any screen, ViewModel, composable, or business logic.
- Changing the app's `android:roundIcon` attribute.

## Decisions

### Decision: Preserve icon filename `ic_launcher.png`

**Choice**: Keep the icon filename as `ic_launcher.png` in all mipmap directories.

**Rationale**: `AndroidManifest.xml` already references `@mipmap/ic_launcher`. Renaming would require a manifest change and could break existing device shortcuts on users' home screens.

**Alternatives considered**:
- Rename to `ic_launcher_new.png` and update manifest — unnecessary complexity, no benefit.

### Decision: Simple file copy, no adaptive icon XML

**Choice**: Copy the flat PNG files directly to the mipmap directories.

**Rationale**: The icon pack provides pre-exported flat PNGs at correct densities. Introducing adaptive icon XML (`mipmap-anydpi-v26/`) would require foreground/background layer artwork which is not provided.

**Alternatives considered**:
- Wrapping the flat icon as the foreground layer of an adaptive icon — requires cropping/safe-zone review and background color decision. Deferred to a future design iteration.

### Decision: Store `playstore-icon.png` at project root level

**Choice**: Place the Play Store icon at `iconikai-icon-pack/android/playstore-icon.png` (already there) and document its location in the README. No copy needed inside `app/`.

**Rationale**: Play Store icons are not bundled inside the APK; they're uploaded manually to the Play Console. Keeping it in the icon pack source folder prevents it from inflating app size.

## Risks / Trade-offs

- **Risk**: Overwriting files irreversibly without a backup.
  → **Mitigation**: Git version control means old icons can always be restored via `git checkout`.
- **Risk**: Incorrect density mapping (e.g., putting xxhdpi icon in xhdpi folder).
  → **Mitigation**: The icon pack folder names match Android conventions exactly; copy directory-to-directory.
- **Risk**: Rounded icon (`android:roundIcon`) still pointing to old resource or not defined.
  → **Mitigation**: Verify `AndroidManifest.xml` and `mipmap-*` do not include a separate `ic_launcher_round.png`. If they do, the same flat icon can be reused.

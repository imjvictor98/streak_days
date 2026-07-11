## Why

The app currently uses the default or outdated launcher icon, which does not reflect the StreakDays brand identity. A new professionally designed icon pack (`iconikai-icon-pack`) has been provided, containing adaptive icon assets for all Android mipmap densities (mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi) plus a Play Store icon. Updating the icon now improves app store presence and visual consistency.

## What Changes

- Replace the existing launcher icon files in all `mipmap-*` resource directories under `app/src/main/res/` with the new assets from `iconikai-icon-pack/android/`.
- Copy the Play Store icon (`playstore-icon.png`) to a known location (e.g., `app/src/main/res/playstore-icon.png` or project root) for use in publishing.
- No changes to `AndroidManifest.xml` are required as long as the `ic_launcher` name is preserved.

## Capabilities

### New Capabilities
<!-- No net-new capabilities; this is an asset replacement. -->
- (none)

### Modified Capabilities
<!-- No spec-level behavioral changes; this is a visual asset update only. -->
- (none)

## Impact

- **Module affected**: `app`
- **Files affected**: `app/src/main/res/mipmap-mdpi/ic_launcher.png`, `app/src/main/res/mipmap-hdpi/ic_launcher.png`, `app/src/main/res/mipmap-xhdpi/ic_launcher.png`, `app/src/main/res/mipmap-xxhdpi/ic_launcher.png`, `app/src/main/res/mipmap-xxxhdpi/ic_launcher.png`
- **No new permissions** required
- **No navigation or screen changes**
- **Min SDK / Target SDK**: unchanged
- **No new dependencies**
- The icon name `ic_launcher` must be preserved to avoid breaking the `AndroidManifest.xml` reference

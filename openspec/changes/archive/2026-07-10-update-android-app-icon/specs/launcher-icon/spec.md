## ADDED Requirements

### Requirement: App launcher icon reflects the new brand design
The app module SHALL display the new StreakDays brand icon (from `iconikai-icon-pack`) as its launcher icon on all supported Android densities.

#### Scenario: Icon displayed at correct density on device home screen
- **WHEN** the app is installed on a device
- **THEN** the system SHALL render the launcher icon from the matching mipmap density folder (`mipmap-mdpi`, `mipmap-hdpi`, `mipmap-xhdpi`, `mipmap-xxhdpi`, or `mipmap-xxxhdpi`)

#### Scenario: Manifest reference remains valid after icon replacement
- **WHEN** the new `ic_launcher.png` files replace the old ones in all mipmap directories
- **THEN** the `android:icon="@mipmap/ic_launcher"` reference in `AndroidManifest.xml` SHALL resolve correctly without any manifest changes

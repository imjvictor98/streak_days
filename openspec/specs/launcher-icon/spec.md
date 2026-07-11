## Purpose

Defines the requirements for the StreakDays app launcher icon on Android, covering all mipmap densities and adaptive icon support.

## Requirements

### Requirement: App launcher icon reflects the new brand design
The app module SHALL display the StreakDays brand icon as its launcher icon on all supported Android densities.

#### Scenario: Icon displayed at correct density on device home screen
- **WHEN** the app is installed on a device
- **THEN** the system SHALL render the launcher icon from the matching mipmap density folder (`mipmap-mdpi`, `mipmap-hdpi`, `mipmap-xhdpi`, `mipmap-xxhdpi`, or `mipmap-xxxhdpi`)

#### Scenario: Manifest reference remains valid after icon replacement
- **WHEN** launcher icon files are updated in all mipmap directories
- **THEN** the `android:icon="@mipmap/ic_launcher"` reference in `AndroidManifest.xml` SHALL resolve correctly without any manifest changes

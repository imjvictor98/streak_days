## 1. Verify Source Assets

- [x] 1.1 Confirm all five density folders exist in `iconikai-icon-pack/android/` (`mipmap-mdpi`, `mipmap-hdpi`, `mipmap-xhdpi`, `mipmap-xxhdpi`, `mipmap-xxxhdpi`) each containing `ic_launcher.png`
- [x] 1.2 Confirm `iconikai-icon-pack/android/playstore-icon.png` exists

## 2. Audit Existing Icon Resources

- [x] 2.1 Inspect `app/src/main/res/` to identify all existing mipmap directories and icon filenames (especially check for `ic_launcher_round.png` or any XML-based adaptive icons in `mipmap-anydpi-v26/`)
- [x] 2.2 Verify `AndroidManifest.xml` references `android:icon="@mipmap/ic_launcher"` — confirm no extra `android:roundIcon` pointing to a separate file that would need updating

## 3. Replace Icon Files

- [x] 3.1 Copy `iconikai-icon-pack/android/mipmap-mdpi/ic_launcher.png` → `app/src/main/res/mipmap-mdpi/ic_launcher.png`
- [x] 3.2 Copy `iconikai-icon-pack/android/mipmap-hdpi/ic_launcher.png` → `app/src/main/res/mipmap-hdpi/ic_launcher.png`
- [x] 3.3 Copy `iconikai-icon-pack/android/mipmap-xhdpi/ic_launcher.png` → `app/src/main/res/mipmap-xhdpi/ic_launcher.png`
- [x] 3.4 Copy `iconikai-icon-pack/android/mipmap-xxhdpi/ic_launcher.png` → `app/src/main/res/mipmap-xxhdpi/ic_launcher.png`
- [x] 3.5 Copy `iconikai-icon-pack/android/mipmap-xxxhdpi/ic_launcher.png` → `app/src/main/res/mipmap-xxxhdpi/ic_launcher.png`

## 4. Handle Round Icon (if applicable)

- [x] 4.1 If `ic_launcher_round.png` exists in any mipmap directory, copy the corresponding new icon as the round variant (same flat PNG) to each mipmap density folder as `ic_launcher_round.png`

## 5. Verify Build

- [x] 5.1 Run `./gradlew :app:assembleDebug` and confirm it succeeds with no resource errors
- [ ] 5.2 Install the debug APK on an emulator or physical device and verify the new launcher icon appears on the home screen

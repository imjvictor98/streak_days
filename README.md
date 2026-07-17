# Streak Days

A premium Android application designed to help you build habits and track your streaks effortlessly.

## App Overview
Streak Days is a modern, beautifully designed habit tracker. It allows users to set goals, monitor their progress, and visualize their daily achievements. Built with a focus on user experience and a sleek design language, it helps you stay motivated and build lasting habits.

## Main Features
- **Goal Creation:** Create custom goals with target durations and start dates, featuring a sleek, fully rounded UI with leading icons and a hero graphic (`create-goal-ui`).
- **Automatic Streaks:** Automatically tracks and calculates your daily streaks, removing the friction of manual logging (`automatic-streaks`).
- **Home Screen Widgets:** Keep your goals front and center with beautifully designed Android widgets. Uses `AlarmManager` for precise midnight synchronization to bypass Doze Mode and instantly refreshes when the app opens (`widget`, `widget-daily-refresh`).
- **Modern Design System:** Adheres to a strict design language with semantic theme colors (`PrimaryContainer`, `PrimaryVariant`), ensuring seamless Light and Dark mode experiences.
- **English Localization:** Strictly follows Android string resources (`strings.xml`) for all user-facing text, maintaining an English-only standardization (`string-resources`).

## Tech Stack & Architecture
- **Platform:** Android (Min SDK 24+)
- **Language:** Kotlin
- **Architecture:** MVVM (ViewModel + StateFlow) with Clean Architecture principles (`architecture-refactor`).
- **UI:** Jetpack Compose, strictly following modern compose guidelines and Compound Component patterns (`compose-guidelines`).
- **Dependency Injection:** Hilt
- **Async Operations:** Kotlin Coroutines & Flow
- **Persistence:** Room Database
- **Testing:** JUnit5, MockK, Turbine, Espresso/Compose UI Test
- **Style/Linting:** ktlint & detekt

---
*This README is maintained automatically via OpenSpec.*

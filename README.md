# StreakDays 📅

**StreakDays** is an Android habit and goal tracking application designed to help you maintain your momentum effortlessly. With automatic calendar-based streak calculations and real-time home screen widgets, you never lose sight of your goals. 

What makes this repository special? It features **Living Documentation**! Every time a new feature is archived via OpenSpec, the AI Agent automatically regenerates this README to keep it perfectly in sync with the codebase. ✨

---

## 🚀 Main Features

### ⏱️ Automatic Streaks
- The app computes streak counts dynamically based on calendar days from the start date (or last relapse date). 
- No manual daily check-ins required! Streaks increment automatically as days pass.

### 🧩 Real-time Widgets
- **Glance-powered Widgets:** Keep your streaks right on your home screen.
- **Midnight & Periodic Sync:** Widgets automatically refresh at midnight (via WorkManager) and periodically every 30 minutes.
- **Immediate Updates:** Adding, deleting, or relapsing a goal triggers an instant widget update so your home screen is never out of sync.

### 🤖 Living Documentation
- This `README.md` is generated and updated automatically by an AI Agent during the OpenSpec `/opsx-archive` workflow based on the actual specs (`openspec/specs/`).

---

## 🏗️ Tech Stack & Architecture

StreakDays is built using modern Android development practices and strict architectural guidelines:
[README.md](README.md)
- **Platform & Language:** Android, Kotlin
- **UI Framework:** Jetpack Compose (and Jetpack Glance for Widgets)
- **Architecture:** Clean Architecture (Package-by-Feature structure, pure Domain layer isolated from Android frameworks, Use Cases for business logic) and MVVM.
- **Dependency Injection:** Hilt
- **Asynchrony:** Kotlin Coroutines & Flow
- **Local Persistence:** Room Database
- **Networking:** Retrofit + OkHttp + Moshi 
- **Testing:** JUnit5, MockK, Turbine, Espresso, Compose UI Test

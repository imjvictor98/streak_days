package com.assabloy.livvi.streakdays.core.domain.model

import java.time.LocalDate

data class Goal(
    val id: Long,
    val name: String,
    val targetDurationDays: Int,
    val startDate: LocalDate,
    val currentStreakDays: Int,
    val longestStreakDays: Int,
    val isCompleted: Boolean,
    val pastStreaks: List<Streak>
)

data class Streak(
    val startDate: LocalDate,
    val endDate: LocalDate?, // null if it's the current active streak
    val lengthInDays: Int
)

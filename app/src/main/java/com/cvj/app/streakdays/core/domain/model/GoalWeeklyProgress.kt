package com.cvj.app.streakdays.core.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

enum class DailyProgressState {
    COMPLETED,
    TODAY_PENDING,
    FUTURE_OR_MISSED
}

fun Goal.getWeeklyProgress(today: LocalDate = LocalDate.now()): List<DailyProgressState> {
    val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
    val hasNoCompletedDays = currentStreakDays == 0
    
    val streakStartDate = if (hasNoCompletedDays) {
        today.plusDays(1)
    } else {
        today.minusDays(currentStreakDays - 1L)
    }

    return (0..6L).map { i ->
        val currentDay = startOfWeek.plusDays(i)
        
        when {
            currentDay.isAfter(today) -> DailyProgressState.FUTURE_OR_MISSED
            currentDay == today -> {
                if (hasNoCompletedDays) DailyProgressState.FUTURE_OR_MISSED else DailyProgressState.COMPLETED
            }
            currentDay.isBefore(streakStartDate) -> DailyProgressState.FUTURE_OR_MISSED
            else -> DailyProgressState.COMPLETED
        }
    }
}

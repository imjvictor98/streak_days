package com.assabloy.livvi.streakdays.core.domain

import com.assabloy.livvi.streakdays.core.domain.model.Goal
import com.assabloy.livvi.streakdays.core.domain.model.Streak
import java.time.LocalDate
import java.time.temporal.ChronoUnit

object StreakCalculator {

    fun calculate(
        goalId: Long,
        name: String,
        targetDurationDays: Int,
        startDate: LocalDate,
        relapseDates: List<LocalDate>,
        currentDate: LocalDate = LocalDate.now()
    ): Goal {
        val sortedRelapses = relapseDates.sorted()

        val pastStreaks = mutableListOf<Streak>()
        var currentStreakStart = startDate

        for (relapseDate in sortedRelapses) {
            if (!relapseDate.isBefore(currentStreakStart)) {
                val length = ChronoUnit.DAYS.between(currentStreakStart, relapseDate).toInt()
                pastStreaks.add(Streak(currentStreakStart, relapseDate, length))
                currentStreakStart = relapseDate.plusDays(1)
            }
        }

        val currentStreakLength = if (currentDate.isBefore(currentStreakStart)) {
            0
        } else {
            ChronoUnit.DAYS.between(currentStreakStart, currentDate).toInt() + 1
        }
        
        val allStreaks = pastStreaks + Streak(currentStreakStart, null, currentStreakLength)
        
        val longestStreakDays = allStreaks.maxOfOrNull { it.lengthInDays } ?: 0
        val isCompleted = currentStreakLength >= targetDurationDays

        return Goal(
            id = goalId,
            name = name,
            targetDurationDays = targetDurationDays,
            startDate = startDate,
            currentStreakDays = currentStreakLength,
            longestStreakDays = longestStreakDays,
            isCompleted = isCompleted,
            pastStreaks = pastStreaks.reversed()
        )
    }
}

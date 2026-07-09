package com.assabloy.livvi.streakdays.domain.model

import com.assabloy.livvi.streakdays.core.domain.model.DailyProgressState
import com.assabloy.livvi.streakdays.core.domain.model.Goal
import com.assabloy.livvi.streakdays.core.domain.model.getWeeklyProgress
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class GoalWeeklyProgressTest {

    private fun createGoal(currentStreakDays: Int): Goal {
        return Goal(
            id = 1L,
            name = "Test",
            targetDurationDays = 30,
            startDate = LocalDate.now().minusDays(10),
            currentStreakDays = currentStreakDays,
            longestStreakDays = currentStreakDays,
            isCompleted = false,
            pastStreaks = emptyList()
        )
    }

    @Test
    fun `test zero streak gives all future_or_missed except today`() {
        val today = LocalDate.of(2023, 10, 18) // Wednesday
        val goal = createGoal(0)
        
        val progress = goal.getWeeklyProgress(today)
        
        assertEquals(7, progress.size)
        // All should be FUTURE_OR_MISSED
        progress.forEach { 
            assertEquals(DailyProgressState.FUTURE_OR_MISSED, it) 
        }
    }

    @Test
    fun `test 1 day streak gives today completed and past future_or_missed`() {
        val today = LocalDate.of(2023, 10, 18) // Wednesday
        val goal = createGoal(1)
        
        val progress = goal.getWeeklyProgress(today)
        
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[0]) // Sunday
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[1]) // Monday
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[2]) // Tuesday
        assertEquals(DailyProgressState.COMPLETED, progress[3]) // Wednesday (today)
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[4]) // Thursday
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[5]) // Friday
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[6]) // Saturday
    }

    @Test
    fun `test 3 day streak gives mon tue wed completed`() {
        val today = LocalDate.of(2023, 10, 18) // Wednesday
        val goal = createGoal(3)
        
        val progress = goal.getWeeklyProgress(today)
        
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[0]) // Sunday
        assertEquals(DailyProgressState.COMPLETED, progress[1]) // Monday
        assertEquals(DailyProgressState.COMPLETED, progress[2]) // Tuesday
        assertEquals(DailyProgressState.COMPLETED, progress[3]) // Wednesday (today)
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[4]) // Thursday
    }

    @Test
    fun `test 10 day streak gives all days up to today completed`() {
        val today = LocalDate.of(2023, 10, 18) // Wednesday
        val goal = createGoal(10) // Covers entire week up to Wed
        
        val progress = goal.getWeeklyProgress(today)
        
        assertEquals(DailyProgressState.COMPLETED, progress[0]) // Sunday
        assertEquals(DailyProgressState.COMPLETED, progress[1]) // Monday
        assertEquals(DailyProgressState.COMPLETED, progress[2]) // Tuesday
        assertEquals(DailyProgressState.COMPLETED, progress[3]) // Wednesday (today)
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[4]) // Thursday
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[5]) // Friday
        assertEquals(DailyProgressState.FUTURE_OR_MISSED, progress[6]) // Saturday
    }
}

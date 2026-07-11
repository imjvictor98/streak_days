package com.cvj.app.streakdays.domain

import com.cvj.app.streakdays.core.domain.StreakCalculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class StreakCalculatorTest {

    @Test
    fun `test calculate with no relapses`() {
        val startDate = LocalDate.of(2023, 1, 1)
        val currentDate = LocalDate.of(2023, 1, 10)

        val result = StreakCalculator.calculate(1L, "No Sugar", 30, startDate, emptyList(), currentDate)

        assertEquals(10, result.currentStreakDays) // 1st to 10th is 9 days between + 1
        assertEquals(10, result.longestStreakDays)
        assertFalse(result.isCompleted)
        assertEquals(0, result.pastStreaks.size)
    }

    @Test
    fun `test calculate with relapses`() {
        val startDate = LocalDate.of(2023, 1, 1)
        val currentDate = LocalDate.of(2023, 1, 15)

        // Relapse on Jan 5
        val relapse1 = LocalDate.of(2023, 1, 5)
        
        // Relapse on Jan 10
        val relapse2 = LocalDate.of(2023, 1, 10)

        val result = StreakCalculator.calculate(1L, "No Sugar", 30, startDate, listOf(relapse1, relapse2), currentDate)

        // Past streak 1: Jan 1 to Jan 5 = 4 days
        // Past streak 2: Jan 6 to Jan 10 = 4 days
        // Current streak: Jan 11 to Jan 15 = 5 days (4 days diff + 1)

        assertEquals(5, result.currentStreakDays)
        assertEquals(5, result.longestStreakDays)
        assertEquals(2, result.pastStreaks.size)
        // Reversed order in past streaks:
        assertEquals(4, result.pastStreaks[0].lengthInDays) // Jan 6 to Jan 10
        assertEquals(4, result.pastStreaks[1].lengthInDays) // Jan 1 to Jan 5
    }

    @Test
    fun `test target duration achieved`() {
        val startDate = LocalDate.of(2023, 1, 1)
        val currentDate = LocalDate.of(2023, 2, 1)

        val result = StreakCalculator.calculate(1L, "No Sugar", 30, startDate, emptyList(), currentDate)

        assertEquals(32, result.currentStreakDays)
        assertTrue(result.isCompleted)
    }
}

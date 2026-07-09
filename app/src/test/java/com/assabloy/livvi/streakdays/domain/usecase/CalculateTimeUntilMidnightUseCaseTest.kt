package com.assabloy.livvi.streakdays.domain.usecase

import com.assabloy.livvi.streakdays.core.domain.usecase.CalculateTimeUntilMidnightUseCase
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculateTimeUntilMidnightUseCaseTest {

    private val useCase = CalculateTimeUntilMidnightUseCase()

    @Test
    fun `invoke should return a positive time difference`() {
        val result = useCase()
        assertTrue("Time difference should be positive", result > 0)
    }

    @Test
    fun `invoke should return a value less than or equal to 24 hours`() {
        val result = useCase()
        val twentyFourHoursInMillis = 24L * 60 * 60 * 1000
        assertTrue("Time difference should be less than or equal to 24 hours", result <= twentyFourHoursInMillis)
    }
}

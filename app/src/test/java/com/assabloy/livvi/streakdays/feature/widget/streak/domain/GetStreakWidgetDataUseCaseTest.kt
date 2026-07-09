package com.assabloy.livvi.streakdays.feature.widget.streak.domain

import com.assabloy.livvi.streakdays.core.domain.model.Goal
import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import kotlinx.coroutines.flow.first

class GetStreakWidgetDataUseCaseTest {

    private val mockGoal = Goal(
        id = 1,
        name = "Test Goal",
        targetDurationDays = 30,
        startDate = LocalDate.now(),
        currentStreakDays = 1,
        longestStreakDays = 1,
        isCompleted = false,
        pastStreaks = emptyList()
    )

    private val fakeGoalRepository = object : GoalRepository {
        override fun getMainGoal(): Flow<Goal?> = flowOf(mockGoal)
        override fun getAllGoals(): Flow<List<Goal>> = flowOf(emptyList())
        override fun getGoalById(id: Long): Flow<Goal?> = flowOf(null)
        override suspend fun createGoal(name: String, targetDurationDays: Int, startDate: LocalDate): Long = 0L
        override suspend fun logRelapse(goalId: Long, timestamp: java.time.Instant) {}
        override suspend fun deleteGoal(id: Long) {}
    }

    private val useCase = GetStreakWidgetDataUseCase(fakeGoalRepository)

    @Test
    fun `invoke should return the main goal from repository`() = runBlocking {
        val result = useCase().first()
        assertEquals(mockGoal, result)
    }
}

package com.assabloy.livvi.streakdays.core.domain.repository

import com.assabloy.livvi.streakdays.core.domain.model.Goal
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import java.time.LocalDate

interface GoalRepository {
    fun getAllGoals(): Flow<List<Goal>>
    fun getGoalById(id: Long): Flow<Goal?>
    fun getMainGoal(): Flow<Goal?>
    suspend fun createGoal(name: String, targetDurationDays: Int, startDate: LocalDate): Long
    suspend fun logRelapse(goalId: Long, timestamp: Instant)
    suspend fun deleteGoal(id: Long)
}

package com.cvj.app.streakdays.core.data.repository

import com.cvj.app.streakdays.core.data.local.GoalDao
import com.cvj.app.streakdays.core.data.local.RelapseDao
import com.cvj.app.streakdays.core.data.local.entity.Goal as EntityGoal
import com.cvj.app.streakdays.core.data.local.entity.Relapse as EntityRelapse
import com.cvj.app.streakdays.core.domain.repository.GoalRepository
import com.cvj.app.streakdays.core.domain.StreakCalculator
import com.cvj.app.streakdays.core.domain.model.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoalRepositoryImpl @Inject constructor(
    private val goalDao: GoalDao,
    private val relapseDao: RelapseDao
) : GoalRepository {

    override fun getAllGoals(): Flow<List<Goal>> {
        return goalDao.getAllGoalsWithRelapses().map { list ->
            list.map { item ->
                val relapseDates = item.relapses.map { 
                    it.timestamp.atZone(ZoneId.systemDefault()).toLocalDate() 
                }
                StreakCalculator.calculate(
                    goalId = item.goal.id,
                    name = item.goal.name,
                    targetDurationDays = item.goal.targetDurationDays,
                    startDate = item.goal.startDate,
                    relapseDates = relapseDates
                )
            }
        }
    }

    override fun getGoalById(id: Long): Flow<Goal?> {
        return goalDao.getGoalWithRelapsesById(id).map { item ->
            item?.let {
                val relapseDates = it.relapses.map { relapse ->
                    relapse.timestamp.atZone(ZoneId.systemDefault()).toLocalDate() 
                }
                StreakCalculator.calculate(
                    goalId = it.goal.id,
                    name = it.goal.name,
                    targetDurationDays = it.goal.targetDurationDays,
                    startDate = it.goal.startDate,
                    relapseDates = relapseDates
                )
            }
        }
    }

    override fun getMainGoal(): Flow<Goal?> {
        return getAllGoals().map { it.firstOrNull() }
    }

    override suspend fun createGoal(name: String, targetDurationDays: Int, startDate: LocalDate): Long {
        val entity = EntityGoal(
            name = name,
            targetDurationDays = targetDurationDays,
            startDate = startDate
        )
        return goalDao.insertGoal(entity)
    }

    override suspend fun logRelapse(goalId: Long, timestamp: Instant) {
        val relapse = EntityRelapse(
            goalId = goalId,
            timestamp = timestamp
        )
        relapseDao.insertRelapse(relapse)
    }

    override suspend fun deleteGoal(id: Long) {
        goalDao.deleteGoal(id)
    }
}

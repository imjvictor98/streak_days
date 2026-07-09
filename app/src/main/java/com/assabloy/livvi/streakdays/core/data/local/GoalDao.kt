package com.assabloy.livvi.streakdays.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Transaction
import com.assabloy.livvi.streakdays.core.data.local.entity.Goal
import com.assabloy.livvi.streakdays.core.data.local.entity.GoalWithRelapses
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals ORDER BY id DESC")
    fun getAllGoals(): Flow<List<Goal>>

    @Query("SELECT * FROM goals WHERE id = :id")
    fun getGoalById(id: Long): Flow<Goal?>

    @Transaction
    @Query("SELECT * FROM goals ORDER BY id DESC")
    fun getAllGoalsWithRelapses(): Flow<List<GoalWithRelapses>>

    @Transaction
    @Query("SELECT * FROM goals WHERE id = :id")
    fun getGoalWithRelapsesById(id: Long): Flow<GoalWithRelapses?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal): Long

    @Update
    suspend fun updateGoal(goal: Goal)

    @Query("DELETE FROM goals WHERE id = :id")
    suspend fun deleteGoal(id: Long)
}

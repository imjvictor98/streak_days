package com.cvj.app.streakdays.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cvj.app.streakdays.core.data.local.entity.Relapse
import kotlinx.coroutines.flow.Flow

@Dao
interface RelapseDao {
    @Query("SELECT * FROM relapses WHERE goalId = :goalId ORDER BY timestamp DESC")
    fun getRelapsesForGoal(goalId: Long): Flow<List<Relapse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelapse(relapse: Relapse): Long

    @Query("DELETE FROM relapses WHERE id = :id")
    suspend fun deleteRelapse(id: Long)
}

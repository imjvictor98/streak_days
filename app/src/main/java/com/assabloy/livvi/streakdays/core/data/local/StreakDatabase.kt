package com.assabloy.livvi.streakdays.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assabloy.livvi.streakdays.core.data.local.entity.Goal
import com.assabloy.livvi.streakdays.core.data.local.entity.Relapse

@Database(entities = [Goal::class, Relapse::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class StreakDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun relapseDao(): RelapseDao
}

package com.assabloy.livvi.streakdays.di

import android.content.Context
import androidx.room.Room
import com.assabloy.livvi.streakdays.core.data.local.GoalDao
import com.assabloy.livvi.streakdays.core.data.local.RelapseDao
import com.assabloy.livvi.streakdays.core.data.local.StreakDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideStreakDatabase(
        @ApplicationContext context: Context
    ): StreakDatabase {
        return Room.databaseBuilder(
            context,
            StreakDatabase::class.java,
            "streak_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGoalDao(database: StreakDatabase): GoalDao {
        return database.goalDao()
    }

    @Provides
    @Singleton
    fun provideRelapseDao(database: StreakDatabase): RelapseDao {
        return database.relapseDao()
    }
}

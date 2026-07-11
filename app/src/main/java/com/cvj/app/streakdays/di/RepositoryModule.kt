package com.cvj.app.streakdays.di

import com.cvj.app.streakdays.core.data.repository.GoalRepositoryImpl
import com.cvj.app.streakdays.core.domain.repository.GoalRepository
import com.cvj.app.streakdays.core.domain.widget.WidgetUpdater
import com.cvj.app.streakdays.feature.widget.streak.presentation.ui.GlanceWidgetUpdater
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGoalRepository(
        goalRepositoryImpl: GoalRepositoryImpl
    ): GoalRepository
    
    @Binds
    abstract fun bindWidgetUpdater(
        glanceWidgetUpdater: GlanceWidgetUpdater
    ): WidgetUpdater
}

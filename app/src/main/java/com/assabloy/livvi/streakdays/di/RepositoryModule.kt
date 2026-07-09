package com.assabloy.livvi.streakdays.di

import com.assabloy.livvi.streakdays.core.data.repository.GoalRepositoryImpl
import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository
import com.assabloy.livvi.streakdays.core.domain.widget.WidgetUpdater
import com.assabloy.livvi.streakdays.feature.widget.streak.presentation.ui.GlanceWidgetUpdater
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

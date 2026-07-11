package com.cvj.app.streakdays.feature.dashboard.domain.usecase

import com.cvj.app.streakdays.core.domain.repository.GoalRepository
import com.cvj.app.streakdays.core.domain.widget.WidgetUpdater
import java.time.Instant
import javax.inject.Inject

class LogRelapseUseCase @Inject constructor(
    private val repository: GoalRepository,
    private val widgetUpdater: WidgetUpdater
) {
    suspend operator fun invoke(goalId: Long) {
        repository.logRelapse(goalId, Instant.now())
        widgetUpdater.updateGoalsWidget()
    }
}

package com.cvj.app.streakdays.feature.details.domain.usecase

import com.cvj.app.streakdays.core.domain.repository.GoalRepository
import com.cvj.app.streakdays.core.domain.widget.WidgetUpdater
import javax.inject.Inject

class DeleteGoalUseCase @Inject constructor(
    private val repository: GoalRepository,
    private val widgetUpdater: WidgetUpdater
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteGoal(id)
        widgetUpdater.updateGoalsWidget()
    }
}

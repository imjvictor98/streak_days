package com.assabloy.livvi.streakdays.feature.details.domain.usecase

import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository
import com.assabloy.livvi.streakdays.core.domain.widget.WidgetUpdater
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

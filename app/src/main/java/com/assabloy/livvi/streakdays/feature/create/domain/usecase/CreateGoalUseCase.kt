package com.assabloy.livvi.streakdays.feature.create.domain.usecase

import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository
import com.assabloy.livvi.streakdays.core.domain.widget.WidgetUpdater
import java.time.LocalDate
import javax.inject.Inject

class CreateGoalUseCase @Inject constructor(
    private val repository: GoalRepository,
    private val widgetUpdater: WidgetUpdater
) {
    suspend operator fun invoke(name: String, targetDurationDays: Int, startDate: LocalDate): Long {
        val id = repository.createGoal(name, targetDurationDays, startDate)
        widgetUpdater.updateGoalsWidget()
        return id
    }
}

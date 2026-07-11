package com.cvj.app.streakdays.feature.widget.streak.domain.usecase

import com.cvj.app.streakdays.core.domain.model.Goal
import com.cvj.app.streakdays.core.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStreakWidgetDataUseCase @Inject constructor(
    private val goalRepository: GoalRepository
) {
    operator fun invoke(): Flow<Goal?> {
        return goalRepository.getMainGoal()
    }
}
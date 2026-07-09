package com.assabloy.livvi.streakdays.feature.widget.streak.domain

import com.assabloy.livvi.streakdays.core.domain.model.Goal
import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStreakWidgetDataUseCase @Inject constructor(
    private val goalRepository: GoalRepository
) {
    operator fun invoke(): Flow<Goal?> {
        return goalRepository.getMainGoal()
    }
}

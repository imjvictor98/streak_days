package com.assabloy.livvi.streakdays.feature.details.domain.usecase

import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository
import javax.inject.Inject

class DeleteGoalUseCase @Inject constructor(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteGoal(id)
    }
}

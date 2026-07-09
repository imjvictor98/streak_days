package com.assabloy.livvi.streakdays.feature.details.domain.usecase

import com.assabloy.livvi.streakdays.core.domain.model.Goal
import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGoalByIdUseCase @Inject constructor(
    private val repository: GoalRepository
) {
    operator fun invoke(id: Long): Flow<Goal?> {
        return repository.getGoalById(id)
    }
}

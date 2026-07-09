package com.assabloy.livvi.streakdays.feature.dashboard.domain.usecase

import com.assabloy.livvi.streakdays.core.domain.model.Goal
import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGoalsUseCase @Inject constructor(
    private val repository: GoalRepository
) {
    operator fun invoke(): Flow<List<Goal>> {
        return repository.getAllGoals()
    }
}

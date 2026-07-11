package com.cvj.app.streakdays.feature.dashboard.domain.usecase

import com.cvj.app.streakdays.core.domain.model.Goal
import com.cvj.app.streakdays.core.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGoalsUseCase @Inject constructor(
    private val repository: GoalRepository
) {
    operator fun invoke(): Flow<List<Goal>> {
        return repository.getAllGoals()
    }
}

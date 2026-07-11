package com.cvj.app.streakdays.feature.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvj.app.streakdays.feature.dashboard.domain.usecase.GetAllGoalsUseCase
import com.cvj.app.streakdays.feature.dashboard.domain.usecase.LogRelapseUseCase
import com.cvj.app.streakdays.core.domain.model.Goal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val getAllGoalsUseCase: GetAllGoalsUseCase,
    private val logRelapseUseCase: LogRelapseUseCase
) : ViewModel() {

    val goals: StateFlow<List<Goal>> = getAllGoalsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun logRelapse(goalId: Long) {
        viewModelScope.launch {
            logRelapseUseCase(goalId)
        }
    }
}

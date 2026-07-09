package com.assabloy.livvi.streakdays.feature.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assabloy.livvi.streakdays.feature.details.domain.usecase.GetGoalByIdUseCase
import com.assabloy.livvi.streakdays.feature.details.domain.usecase.DeleteGoalUseCase
import com.assabloy.livvi.streakdays.feature.dashboard.domain.usecase.LogRelapseUseCase
import com.assabloy.livvi.streakdays.core.domain.model.Goal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGoalByIdUseCase: GetGoalByIdUseCase,
    private val logRelapseUseCase: LogRelapseUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase
) : ViewModel() {

    private val goalId: Long = checkNotNull(savedStateHandle.get<String>("goalId")?.toLongOrNull())

    val goal: StateFlow<Goal?> = getGoalByIdUseCase(goalId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun logRelapse() {
        viewModelScope.launch {
            logRelapseUseCase(goalId)
        }
    }

    fun deleteGoal(onDeleted: () -> Unit) {
        viewModelScope.launch {
            deleteGoalUseCase(goalId)
            onDeleted()
        }
    }
}

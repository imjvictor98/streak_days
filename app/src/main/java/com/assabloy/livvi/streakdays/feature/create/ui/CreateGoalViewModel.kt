package com.assabloy.livvi.streakdays.feature.create.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assabloy.livvi.streakdays.feature.create.domain.usecase.CreateGoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CreateGoalViewModel @Inject constructor(
    private val createGoalUseCase: CreateGoalUseCase
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _durationDays = MutableStateFlow("30")
    val durationDays: StateFlow<String> = _durationDays.asStateFlow()

    private val _startDate = MutableStateFlow(LocalDate.now())
    val startDate: StateFlow<LocalDate> = _startDate.asStateFlow()

    private val _isSaved = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> = _isSaved.asStateFlow()

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateDurationDays(days: String) {
        _durationDays.value = days
    }

    fun updateStartDate(date: LocalDate) {
        _startDate.value = date
    }

    fun saveGoal() {
        viewModelScope.launch {
            val duration = _durationDays.value.toIntOrNull() ?: 30
            createGoalUseCase(
                name = _name.value.ifBlank { "Untitled Goal" },
                targetDurationDays = duration,
                startDate = _startDate.value
            )
            _isSaved.value = true
        }
    }
}

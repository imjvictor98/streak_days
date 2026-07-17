package com.cvj.app.streakdays.feature.create.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvj.app.streakdays.feature.create.domain.usecase.CreateGoalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

@HiltViewModel
class CreateGoalViewModel @Inject constructor(
    private val createGoalUseCase: CreateGoalUseCase
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _durationDays = MutableStateFlow("30")
    val durationDays: StateFlow<String> = _durationDays.asStateFlow()



    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    private val _startDateText = MutableStateFlow(LocalDate.now().format(formatter))
    val startDateText: StateFlow<String> = _startDateText.asStateFlow()

    private val _isSaved = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> = _isSaved.asStateFlow()

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateDurationDays(days: String) {
        _durationDays.value = days
    }

    fun updateStartDateText(dateString: String) {
        _startDateText.value = dateString
    }

    fun saveGoal() {
        viewModelScope.launch {
            val duration = _durationDays.value.toIntOrNull() ?: 30
            val parsedStartDate = try {
                LocalDate.parse(_startDateText.value, formatter)
            } catch (e: DateTimeParseException) {
                LocalDate.now()
            }
            createGoalUseCase(
                name = _name.value.ifBlank { "Untitled Goal" },
                targetDurationDays = duration,
                startDate = parsedStartDate
            )
            _isSaved.value = true
        }
    }
}

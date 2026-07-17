package com.cvj.app.streakdays.feature.create.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cvj.app.streakdays.R
import com.cvj.app.streakdays.core.designsystem.theme.StreakDaysTheme
import com.cvj.app.streakdays.core.ui.components.StreakPrimaryButton
import com.cvj.app.streakdays.core.ui.components.StreakRoundedTextField
import com.cvj.app.streakdays.core.ui.components.StreakTopAppBar
import com.cvj.app.streakdays.core.ui.utils.DateVisualTransformation

@Composable
fun CreateGoalScreen(
    onNavigateBack: () -> Unit,
    viewModel: CreateGoalViewModel = hiltViewModel()
) {
    val name by viewModel.name.collectAsState()
    val duration by viewModel.durationDays.collectAsState()
    val startDateText by viewModel.startDateText.collectAsState()
    val isSaved by viewModel.isSaved.collectAsState()

    LaunchedEffect(isSaved) {
        if (isSaved) {
            onNavigateBack()
        }
    }

    CreateGoalScreenContent(
        name = name,
        duration = duration,
        startDateText = startDateText,
        onNameChange = { viewModel.updateName(it) },
        onDurationChange = { viewModel.updateDurationDays(it) },
        onStartDateChange = { viewModel.updateStartDateText(it) },
        onSaveGoal = { viewModel.saveGoal() },
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGoalScreenContent(
    name: String,
    duration: String,
    startDateText: String,
    onNameChange: (String) -> Unit,
    onDurationChange: (String) -> Unit,
    onStartDateChange: (String) -> Unit,
    onSaveGoal: () -> Unit,
    onNavigateBack: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            StreakTopAppBar(
                title = stringResource(R.string.create_goal_title),
                onNavigationClick = onNavigateBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            
            // Hero Graphic
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .background(color = Color(0x336063EE), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star, // Placeholder for the hero icon
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = Color(0xFF6063EE)
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(40.dp)) {
                // Goal Name
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(R.string.create_goal_name_label),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    StreakRoundedTextField(
                        value = name,
                        onValueChange = onNameChange,
                        placeholder = "e.g. Read 30 books",
                        leadingIcon = Icons.Default.Edit,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Target Duration
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(R.string.create_goal_duration_label),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    StreakRoundedTextField(
                        value = duration,
                        onValueChange = onDurationChange,
                        placeholder = "e.g. 365",
                        leadingIcon = Icons.Default.Info,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Start Date
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Start Date (dd/MM/yyyy)",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    StreakRoundedTextField(
                        value = startDateText,
                        onValueChange = { text ->
                            val digits = text.filter { it.isDigit() }
                            if (digits.length <= 8) onStartDateChange(digits)
                        },
                        placeholder = "dd/MM/yyyy (e.g., 22/01/2026)",
                        leadingIcon = Icons.Default.DateRange,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = DateVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Action Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            ) {
                StreakPrimaryButton(
                    text = stringResource(R.string.create_goal_save_button),
                    onClick = onSaveGoal,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = name.isNotBlank() && duration.toIntOrNull() != null
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
fun CreateGoalScreenPreview_Empty() {
    StreakDaysTheme {
        CreateGoalScreenContent(
            name = "",
            duration = "",
            startDateText = "22/01/2026",
            onNameChange = {},
            onDurationChange = {},
            onStartDateChange = {},
            onSaveGoal = {},
            onNavigateBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
fun CreateGoalScreenPreview_Filled() {
    StreakDaysTheme {
        CreateGoalScreenContent(
            name = "Read 10 pages",
            duration = "30",
            startDateText = "22/01/2026",
            onNameChange = {},
            onDurationChange = {},
            onStartDateChange = {},
            onSaveGoal = {},
            onNavigateBack = {}
        )
    }
}

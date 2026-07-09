package com.assabloy.livvi.streakdays.feature.create.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assabloy.livvi.streakdays.core.designsystem.StreakDaysTheme

@Composable
fun CreateGoalScreen(
    onNavigateBack: () -> Unit,
    viewModel: CreateGoalViewModel = hiltViewModel()
) {
    val name by viewModel.name.collectAsState()
    val duration by viewModel.durationDays.collectAsState()
    val isSaved by viewModel.isSaved.collectAsState()

    LaunchedEffect(isSaved) {
        if (isSaved) {
            onNavigateBack()
        }
    }

    CreateGoalScreenContent(
        name = name,
        duration = duration,
        onNameChange = { viewModel.updateName(it) },
        onDurationChange = { viewModel.updateDurationDays(it) },
        onSaveGoal = { viewModel.saveGoal() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGoalScreenContent(
    name: String,
    duration: String,
    onNameChange: (String) -> Unit,
    onDurationChange: (String) -> Unit,
    onSaveGoal: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create Goal") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Goal Name (e.g. No Sugar)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = duration,
                onValueChange = onDurationChange,
                label = { Text("Target Duration (Days)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text("Start Date: Today")

            Button(
                onClick = onSaveGoal,
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && duration.toIntOrNull() != null
            ) {
                Text("Save Goal")
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
            onNameChange = {},
            onDurationChange = {},
            onSaveGoal = {}
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
            onNameChange = {},
            onDurationChange = {},
            onSaveGoal = {}
        )
    }
}

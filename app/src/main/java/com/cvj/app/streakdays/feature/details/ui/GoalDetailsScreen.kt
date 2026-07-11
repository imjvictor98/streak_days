package com.cvj.app.streakdays.feature.details.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cvj.app.streakdays.core.domain.model.Goal
import com.cvj.app.streakdays.core.domain.model.Streak
import com.cvj.app.streakdays.core.designsystem.theme.StreakDaysTheme
import androidx.compose.ui.res.stringResource
import java.time.LocalDate
import com.cvj.app.streakdays.R
@Composable
fun GoalDetailsScreen(
    onNavigateBack: () -> Unit,
    viewModel: GoalDetailsViewModel = hiltViewModel()
) {
    val goal by viewModel.goal.collectAsState()

    GoalDetailsScreenContent(
        goal = goal,
        onNavigateBack = onNavigateBack,
        onLogRelapse = { viewModel.logRelapse() },
        onDeleteGoal = { viewModel.deleteGoal(onDeleted = onNavigateBack) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalDetailsScreenContent(
    goal: Goal?,
    onNavigateBack: () -> Unit,
    onLogRelapse: () -> Unit,
    onDeleteGoal: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(goal?.name ?: stringResource(R.string.goal_details_title_fallback)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.goal_details_back_cd))
                    }
                }
            )
        }
    ) { padding ->
        if (goal == null) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            val nonNullGoal = goal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(stringResource(R.string.goal_details_current_streak, nonNullGoal.currentStreakDays), style = MaterialTheme.typography.headlineMedium)
                Text(stringResource(R.string.goal_details_longest_streak, nonNullGoal.longestStreakDays), style = MaterialTheme.typography.titleLarge)
                
                Button(onClick = onLogRelapse, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.action_log_relapse))
                }

                Button(
                    onClick = onDeleteGoal,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text(stringResource(R.string.goal_details_delete_button))
                }

                HorizontalDivider()

                Text(stringResource(R.string.goal_details_past_streaks_title), style = MaterialTheme.typography.titleLarge)
                
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(nonNullGoal.pastStreaks) { streak ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(stringResource(R.string.goal_details_streak_length, streak.lengthInDays))
                                Text(stringResource(R.string.goal_details_streak_from, streak.startDate.toString()))
                                Text(stringResource(R.string.goal_details_streak_to, streak.endDate?.toString() ?: stringResource(R.string.goal_details_streak_present)))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
fun GoalDetailsScreenPreview_Loading() {
    StreakDaysTheme {
        GoalDetailsScreenContent(
            goal = null,
            onNavigateBack = {},
            onLogRelapse = {},
            onDeleteGoal = {}
        )
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
fun GoalDetailsScreenPreview_Loaded() {
    val sampleGoal = Goal(
        id = 1L,
        name = "Read 10 pages",
        targetDurationDays = 30,
        startDate = LocalDate.now().minusDays(5),
        currentStreakDays = 6,
        longestStreakDays = 12,
        isCompleted = false,
        pastStreaks = listOf(
            Streak(startDate = LocalDate.now().minusDays(30), endDate = LocalDate.now().minusDays(18), lengthInDays = 12),
            Streak(startDate = LocalDate.now().minusDays(15), endDate = LocalDate.now().minusDays(10), lengthInDays = 5)
        )
    )
    StreakDaysTheme {
        GoalDetailsScreenContent(
            goal = sampleGoal,
            onNavigateBack = {},
            onLogRelapse = {},
            onDeleteGoal = {}
        )
    }
}

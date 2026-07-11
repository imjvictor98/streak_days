package com.assabloy.livvi.streakdays.feature.dashboard.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assabloy.livvi.streakdays.core.domain.model.Goal
import com.assabloy.livvi.streakdays.core.designsystem.StreakDaysTheme
import com.assabloy.livvi.streakdays.feature.dashboard.ui.components.GoalCard
import androidx.compose.ui.res.stringResource
import com.assabloy.livvi.streakdays.R
import java.time.LocalDate
@Composable
fun DashboardScreen(
    onNavigateToCreateGoal: () -> Unit,
    onNavigateToGoalDetails: (Long) -> Unit,
    viewModel: GoalsViewModel = hiltViewModel()
) {
    val goals by viewModel.goals.collectAsState()

    DashboardScreenContent(
        goals = goals,
        onNavigateToCreateGoal = onNavigateToCreateGoal,
        onNavigateToGoalDetails = onNavigateToGoalDetails,
        onLogRelapse = { viewModel.logRelapse(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenContent(
    goals: List<Goal>,
    onNavigateToCreateGoal: () -> Unit,
    onNavigateToGoalDetails: (Long) -> Unit,
    onLogRelapse: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold 
                    ) 
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreateGoal,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.dashboard_create_goal_cd))
            }
        }
    ) { padding ->
        if (goals.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(32.dp), 
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Add, 
                    contentDescription = null, 
                    modifier = Modifier.size(64.dp), 
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.dashboard_empty_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.dashboard_empty_description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(goals) { goal ->
                    val progress = if (goal.targetDurationDays > 0) {
                        goal.currentStreakDays.toFloat() / goal.targetDurationDays
                    } else 0f
                    val isCompleted = goal.isCompleted || goal.currentStreakDays >= goal.targetDurationDays

                    GoalCard(
                        isCompleted = isCompleted,
                        onClick = { onNavigateToGoalDetails(goal.id) }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = androidx.compose.ui.Alignment.Top
                        ) {
                            Header(
                                title = goal.name,
                                subtitle = if (isCompleted) stringResource(R.string.dashboard_goal_target_achieved) else stringResource(R.string.dashboard_goal_keep_going),
                                isCompleted = isCompleted,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            ProgressRing(
                                progress = progress,
                                label = "${(progress * 100).toInt()}%",
                                isCompleted = isCompleted,
                                modifier = Modifier.size(64.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        StatsRow(
                            currentStreak = goal.currentStreakDays,
                            bestStreak = goal.longestStreakDays,
                            target = goal.targetDurationDays,
                            isCompleted = isCompleted,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ActionArea(modifier = Modifier) {
                            OutlinedButton(
                                onClick = { onLogRelapse(goal.id) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = MaterialTheme.colorScheme.error
                                ),
                                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.5f))
                            ) {
                                Text(stringResource(R.string.dashboard_goal_log_relapse))
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
fun DashboardScreenPreview_Empty() {
    StreakDaysTheme {
        DashboardScreenContent(
            goals = emptyList(),
            onNavigateToCreateGoal = {},
            onNavigateToGoalDetails = {},
            onLogRelapse = {}
        )
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
fun DashboardScreenPreview_WithGoals() {
    val sampleGoals = listOf(
        Goal(id = 1L, name = "Read 10 pages", targetDurationDays = 30, startDate = LocalDate.now().minusDays(5), currentStreakDays = 6, longestStreakDays = 12, isCompleted = false, pastStreaks = emptyList()),
        Goal(id = 2L, name = "Meditate", targetDurationDays = 30, startDate = LocalDate.now().minusDays(30), currentStreakDays = 31, longestStreakDays = 31, isCompleted = true, pastStreaks = emptyList())
    )
    StreakDaysTheme {
        DashboardScreenContent(
            goals = sampleGoals,
            onNavigateToCreateGoal = {},
            onNavigateToGoalDetails = {},
            onLogRelapse = {}
        )
    }
}

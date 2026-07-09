package com.assabloy.livvi.streakdays.feature.dashboard.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
                        text = "StreakDays", 
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
                Icon(Icons.Filled.Add, contentDescription = "Create Goal")
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
                    text = "No goals yet", 
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tap the + button to create your first goal and start building your streak!", 
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
                    GoalCard(
                        goal = goal,
                        onClick = { onNavigateToGoalDetails(goal.id) },
                        onLogRelapse = { onLogRelapse(goal.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun StreakProgressRing(
    progress: Float,
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    trackColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.surfaceVariant,
    strokeWidth: androidx.compose.ui.unit.Dp = 8.dp,
    centerContent: @Composable () -> Unit = {}
) {
    Box(modifier = modifier, contentAlignment = androidx.compose.ui.Alignment.Center) {
        CircularProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxSize(),
            color = color,
            trackColor = trackColor,
            strokeWidth = strokeWidth,
            strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
        )
        centerContent()
    }
}

@Composable
fun StreakStatsText(
    currentStreak: Int,
    longestStreak: Int,
    target: Int,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    val textColor = if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
    val labelColor = if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onSurfaceVariant
    val highlightColor = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary
    
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Column {
            Text("Current", style = MaterialTheme.typography.labelMedium, color = labelColor)
            Text("$currentStreak", style = MaterialTheme.typography.titleLarge, color = highlightColor, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }
        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            Text("Best", style = MaterialTheme.typography.labelMedium, color = labelColor)
            Text("$longestStreak", style = MaterialTheme.typography.titleMedium, color = textColor, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
        }
        Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
            Text("Target", style = MaterialTheme.typography.labelMedium, color = labelColor)
            Text("$target", style = MaterialTheme.typography.titleMedium, color = textColor, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
        }
    }
}

@Composable
fun GoalCard(goal: Goal, onClick: () -> Unit, onLogRelapse: () -> Unit) {
    val progress = if (goal.targetDurationDays > 0) {
        goal.currentStreakDays.toFloat() / goal.targetDurationDays
    } else 0f
    
    val isCompleted = goal.isCompleted || goal.currentStreakDays >= goal.targetDurationDays
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = if (isCompleted) 4.dp else 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = goal.name, 
                        style = MaterialTheme.typography.headlineSmall,
                        color = if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (isCompleted) {
                        Text(
                            text = "Target Achieved! \uD83C\uDF89", 
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
                        )
                    } else {
                        Text(
                            text = "Keep going!", 
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                StreakProgressRing(
                    progress = progress,
                    modifier = Modifier.size(64.dp),
                    color = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary,
                    trackColor = if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        color = if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            StreakStatsText(
                currentStreak = goal.currentStreakDays,
                longestStreak = goal.longestStreakDays,
                target = goal.targetDurationDays,
                isCompleted = isCompleted
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = onLogRelapse,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.5f))
            ) {
                Text("Log Relapse")
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

@Preview(showBackground = true)
@Composable
fun GoalCardPreview_Active() {
    val goal = Goal(id = 1L, name = "Read 10 pages", targetDurationDays = 30, startDate = LocalDate.now().minusDays(5), currentStreakDays = 6, longestStreakDays = 12, isCompleted = false, pastStreaks = emptyList())
    StreakDaysTheme {
        GoalCard(goal = goal, onClick = {}, onLogRelapse = {})
    }
}

@Preview(showBackground = true)
@Composable
fun GoalCardPreview_Completed() {
    val goal = Goal(id = 2L, name = "Meditate", targetDurationDays = 30, startDate = LocalDate.now().minusDays(30), currentStreakDays = 31, longestStreakDays = 31, isCompleted = true, pastStreaks = emptyList())
    StreakDaysTheme {
        GoalCard(goal = goal, onClick = {}, onLogRelapse = {})
    }
}

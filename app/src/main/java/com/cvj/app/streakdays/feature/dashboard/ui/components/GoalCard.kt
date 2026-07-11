package com.cvj.app.streakdays.feature.dashboard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cvj.app.streakdays.core.designsystem.theme.StreakDaysTheme

@Composable
fun GoalCard(
    isCompleted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable GoalCardScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = if (isCompleted) 4.dp else 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            GoalCardScopeImpl.content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoalCardPreview_Active() {
    StreakDaysTheme {
        GoalCard(isCompleted = false, onClick = {}) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Header(
                    title = "Read 10 pages",
                    subtitle = "Keep going!",
                    isCompleted = false,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                ProgressRing(
                    progress = 0.5f,
                    label = "50%",
                    isCompleted = false,
                    modifier = Modifier.size(64.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            StatsRow(
                currentStreak = 15,
                bestStreak = 20,
                target = 30,
                isCompleted = false,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            ActionArea(modifier = Modifier) {
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.5f))
                ) {
                    Text("Log Relapse")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoalCardPreview_Completed() {
    StreakDaysTheme {
        GoalCard(isCompleted = true, onClick = {}) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Header(
                    title = "Meditate",
                    subtitle = "Target Achieved! \uD83C\uDF89",
                    isCompleted = true,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                ProgressRing(
                    progress = 1.0f,
                    label = "100%",
                    isCompleted = true,
                    modifier = Modifier.size(64.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            StatsRow(
                currentStreak = 30,
                bestStreak = 30,
                target = 30,
                isCompleted = true,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            ActionArea(modifier = Modifier) {
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.5f))
                ) {
                    Text("Log Relapse")
                }
            }
        }
    }
}

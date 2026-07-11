package com.cvj.app.streakdays.feature.dashboard.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

internal object GoalCardScopeImpl : GoalCardScope {
    @Composable
    override fun Header(title: String, subtitle: String, isCompleted: Boolean, modifier: Modifier) {
        Column(modifier = modifier) {
            Text(
                text = title, 
                style = MaterialTheme.typography.headlineSmall,
                color = if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (isCompleted) {
                Text(
                    text = subtitle, 
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Text(
                    text = subtitle, 
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    @Composable
    override fun ProgressRing(progress: Float, label: String, isCompleted: Boolean, modifier: Modifier) {
        StreakProgressRing(
            progress = progress,
            modifier = modifier,
            color = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary,
            trackColor = if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
            )
        }
    }

    @Composable
    override fun StatsRow(currentStreak: Int, bestStreak: Int, target: Int, isCompleted: Boolean, modifier: Modifier) {
        StreakStatsText(
            currentStreak = currentStreak,
            longestStreak = bestStreak,
            target = target,
            isCompleted = isCompleted,
            modifier = modifier
        )
    }

    @Composable
    override fun ActionArea(modifier: Modifier, content: @Composable () -> Unit) {
        Box(modifier = modifier) {
            content()
        }
    }
}

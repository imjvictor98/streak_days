package com.cvj.app.streakdays.feature.dashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.cvj.app.streakdays.R

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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(stringResource(R.string.dashboard_streak_current), style = MaterialTheme.typography.labelMedium, color = labelColor)
            Text("$currentStreak", style = MaterialTheme.typography.titleLarge, color = highlightColor, fontWeight = FontWeight.Bold)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.dashboard_streak_best), style = MaterialTheme.typography.labelMedium, color = labelColor)
            Text("$longestStreak", style = MaterialTheme.typography.titleMedium, color = textColor, fontWeight = FontWeight.SemiBold)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(stringResource(R.string.dashboard_streak_target), style = MaterialTheme.typography.labelMedium, color = labelColor)
            Text("$target", style = MaterialTheme.typography.titleMedium, color = textColor, fontWeight = FontWeight.SemiBold)
        }
    }
}

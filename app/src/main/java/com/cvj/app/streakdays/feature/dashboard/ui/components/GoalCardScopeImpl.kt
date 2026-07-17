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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.cvj.app.streakdays.core.designsystem.theme.HankenGrotesk
import com.cvj.app.streakdays.core.designsystem.theme.Inter

internal object GoalCardScopeImpl : GoalCardScope {
    @Composable
    override fun Header(title: String, subtitle: String, isCompleted: Boolean, modifier: Modifier) {
        Column(modifier = modifier) {
            Text(
                text = title, 
                style = TextStyle(
                    fontFamily = HankenGrotesk,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color(0xFF191C1E)
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle, 
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color(0xFF777587)
                )
            )
        }
    }

    @Composable
    override fun ProgressArea(progress: Float, label: String, isCompleted: Boolean, modifier: Modifier) {
        StreakProgressRing(
            progress = progress,
            modifier = modifier,
            color = Color(0xFF4D44E3), // Primary or according to Figma
            trackColor = Color(0xFFE2DFEB)
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color(0xFF191C1E)
                )
            )
        }
    }

    @Composable
    override fun StatsArea(currentStreak: Int, bestStreak: Int, target: Int, isCompleted: Boolean, modifier: Modifier) {
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

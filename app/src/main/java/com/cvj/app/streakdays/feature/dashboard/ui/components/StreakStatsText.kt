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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.cvj.app.streakdays.core.designsystem.theme.HankenGrotesk
import com.cvj.app.streakdays.core.designsystem.theme.Inter

@Composable
fun StreakStatsText(
    currentStreak: Int,
    longestStreak: Int,
    target: Int,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    val labelStyle = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = Color(0xFF777587)
    )

    val currentValueStyle = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color(0xFF3525CD)
    )

    val otherValueStyle = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = Color(0xFF191C1E)
    )
    
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(stringResource(R.string.dashboard_streak_current), style = labelStyle)
            Text("$currentStreak", style = currentValueStyle)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.dashboard_streak_best), style = labelStyle)
            Text("$longestStreak", style = otherValueStyle)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(stringResource(R.string.dashboard_streak_target), style = labelStyle)
            Text("$target", style = otherValueStyle)
        }
    }
}

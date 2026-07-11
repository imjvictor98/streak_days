package com.assabloy.livvi.streakdays.feature.dashboard.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface GoalCardScope {
    @Composable
    fun Header(title: String, subtitle: String, isCompleted: Boolean, modifier: Modifier)

    @Composable
    fun ProgressRing(progress: Float, label: String, isCompleted: Boolean, modifier: Modifier)

    @Composable
    fun StatsRow(currentStreak: Int, bestStreak: Int, target: Int, isCompleted: Boolean, modifier: Modifier)

    @Composable
    fun ActionArea(modifier: Modifier, content: @Composable () -> Unit)
}

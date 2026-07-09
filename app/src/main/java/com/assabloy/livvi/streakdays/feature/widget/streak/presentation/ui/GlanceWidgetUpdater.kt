package com.assabloy.livvi.streakdays.feature.widget.streak.presentation.ui

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.assabloy.livvi.streakdays.core.domain.widget.WidgetUpdater
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlanceWidgetUpdater @Inject constructor(
    @ApplicationContext private val context: Context
) : WidgetUpdater {
    override suspend fun updateGoalsWidget() {
        StreakDaysWidget().updateAll(context)
    }
}

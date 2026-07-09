package com.assabloy.livvi.streakdays.feature.widget.streak.presentation.receiver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.assabloy.livvi.streakdays.feature.widget.streak.presentation.ui.StreakDaysWidget

class StreakDaysWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = StreakDaysWidget()
}

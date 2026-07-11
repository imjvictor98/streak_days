package com.cvj.app.streakdays.feature.widget.streak.presentation.receiver

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.cvj.app.streakdays.feature.widget.streak.presentation.ui.StreakDaysWidget
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class StreakDaysWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = StreakDaysWidget()

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        MainScope().launch {
            StreakDaysWidget().updateAll(context)
        }
    }
}


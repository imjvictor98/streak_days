package com.cvj.app.streakdays.feature.widget.streak.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.cvj.app.streakdays.core.domain.widget.WidgetUpdater
import com.cvj.app.streakdays.feature.widget.streak.presentation.worker.WidgetUpdateScheduler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MidnightUpdateReceiver : BroadcastReceiver() {

    @Inject
    lateinit var widgetUpdater: WidgetUpdater

    @Inject
    lateinit var widgetUpdateScheduler: WidgetUpdateScheduler

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "com.cvj.app.streakdays.ACTION_MIDNIGHT_UPDATE") {
            val pendingResult = goAsync()
            MainScope().launch {
                try {
                    widgetUpdater.updateGoalsWidget()
                    widgetUpdateScheduler.scheduleMidnightUpdate()
                } finally {
                    pendingResult.finish()
                }
            }
        }
    }
}

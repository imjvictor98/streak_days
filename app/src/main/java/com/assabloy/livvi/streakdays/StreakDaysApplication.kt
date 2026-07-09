package com.assabloy.livvi.streakdays

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import com.assabloy.livvi.streakdays.feature.widget.streak.presentation.worker.WidgetUpdateScheduler
import javax.inject.Inject

@HiltAndroidApp
class StreakDaysApplication : Application() {

    @Inject
    lateinit var widgetUpdateScheduler: WidgetUpdateScheduler

    override fun onCreate() {
        super.onCreate()
        
        widgetUpdateScheduler.scheduleMidnightUpdate()
    }
}

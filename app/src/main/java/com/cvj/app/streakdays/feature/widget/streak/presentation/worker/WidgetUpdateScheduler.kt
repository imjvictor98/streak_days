package com.cvj.app.streakdays.feature.widget.streak.presentation.worker

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.cvj.app.streakdays.feature.widget.streak.domain.usecase.CalculateTimeUntilMidnightUseCase
import com.cvj.app.streakdays.feature.widget.streak.presentation.receiver.MidnightUpdateReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WidgetUpdateScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val calculateTimeUntilMidnightUseCase: CalculateTimeUntilMidnightUseCase
) {

    companion object {
        private const val ACTION = "com.cvj.app.streakdays.ACTION_MIDNIGHT_UPDATE"
    }

    fun scheduleMidnightUpdate() {
        val timeDiff = calculateTimeUntilMidnightUseCase()
        val triggerTime = System.currentTimeMillis() + timeDiff

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MidnightUpdateReceiver::class.java).apply {
            action = ACTION
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )
    }
}

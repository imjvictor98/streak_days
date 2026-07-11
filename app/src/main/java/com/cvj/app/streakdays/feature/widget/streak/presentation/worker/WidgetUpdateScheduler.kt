package com.cvj.app.streakdays.feature.widget.streak.presentation.worker

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.cvj.app.streakdays.feature.widget.streak.domain.usecase.CalculateTimeUntilMidnightUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WidgetUpdateScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val calculateTimeUntilMidnightUseCase: CalculateTimeUntilMidnightUseCase
) {

    fun scheduleMidnightUpdate() {
        val timeDiff = calculateTimeUntilMidnightUseCase()

        val workRequest = PeriodicWorkRequestBuilder<WidgetDailyUpdateWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "WidgetMidnightUpdate",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
    }
}

package com.cvj.app.streakdays.feature.widget.streak.presentation.worker

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.cvj.app.streakdays.feature.widget.streak.presentation.ui.StreakDaysWidget
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WidgetDailyUpdateWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            StreakDaysWidget().updateAll(context)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}

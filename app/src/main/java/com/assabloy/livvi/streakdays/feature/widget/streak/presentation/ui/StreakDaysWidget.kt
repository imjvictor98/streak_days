package com.assabloy.livvi.streakdays.feature.widget.streak.presentation.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.TextStyle
import com.assabloy.livvi.streakdays.MainActivity
import com.assabloy.livvi.streakdays.R
import com.assabloy.livvi.streakdays.core.domain.model.DailyProgressState
import com.assabloy.livvi.streakdays.core.domain.model.Goal
import com.assabloy.livvi.streakdays.core.domain.model.getWeeklyProgress
import com.assabloy.livvi.streakdays.feature.widget.streak.domain.GetStreakWidgetDataUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.EntryPointAccessors
import androidx.glance.text.Text
import kotlinx.coroutines.flow.firstOrNull

class StreakDaysWidget : GlanceAppWidget() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface WidgetEntryPoint {
        fun getStreakWidgetDataUseCase(): GetStreakWidgetDataUseCase
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            WidgetEntryPoint::class.java
        )
        
        val useCase = entryPoint.getStreakWidgetDataUseCase()
        val mainGoal = useCase().firstOrNull()

        provideContent {
            GlanceTheme {
                StreakDaysWidgetContent(mainGoal = mainGoal)
            }
        }
    }
}

@Composable
fun StreakDaysWidgetContent(mainGoal: Goal?) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(GlanceTheme.colors.surface)
            .padding(16.dp)
            .clickable(actionStartActivity(android.content.Intent(LocalContext.current, MainActivity::class.java))),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.Start
    ) {
        if (mainGoal != null) {
            WidgetHeader(mainGoal)
            Spacer(modifier = GlanceModifier.height(16.dp))
            WidgetWeeklyCalendar(mainGoal)
            Spacer(modifier = GlanceModifier.height(16.dp))
            WidgetProgressBar(mainGoal)
        } else {
            Column(
                modifier = GlanceModifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nenhum objetivo ativo",
                    style = TextStyle(color = GlanceTheme.colors.onSurface)
                )
            }
        }
    }
}

@Composable
fun WidgetHeader(goal: Goal) {
    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = GlanceModifier.defaultWeight()) {
            Text(
                text = "\uD83D\uDD25 STREAK", 
                style = TextStyle(
                    color = GlanceTheme.colors.primary, 
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            )
            Text(
                text = "${goal.currentStreakDays} Dias", 
                style = TextStyle(
                    color = GlanceTheme.colors.onSurface, 
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
        Text(
            text = goal.name,
            style = TextStyle(
                color = GlanceTheme.colors.onSurfaceVariant,
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun WidgetWeeklyCalendar(goal: Goal) {
    val weeklyProgress = goal.getWeeklyProgress()
    val daysOfWeek = listOf("D", "S", "T", "Q", "Q", "S", "S")

    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        weeklyProgress.forEachIndexed { index, state ->
            Column(
                modifier = GlanceModifier.defaultWeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Usaremos textos simples se os resources não existirem, mas o ideal é usar androidx.glance.Image
                // Por agora, vou usar um emoji substituto simples
                val emoji = when (state) {
                    DailyProgressState.COMPLETED -> "✅"
                    DailyProgressState.TODAY_PENDING -> "⭕"
                    DailyProgressState.FUTURE_OR_MISSED -> "⚫"
                }
                Text(text = emoji, style = TextStyle(fontSize = 16.sp))
                
                Spacer(modifier = GlanceModifier.height(4.dp))
                
                Text(
                    text = daysOfWeek[index],
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurfaceVariant,
                        fontSize = 10.sp
                    )
                )
            }
        }
    }
}

@Composable
fun WidgetProgressBar(goal: Goal) {
    val progressPercent = if (goal.targetDurationDays > 0) {
        (goal.currentStreakDays.toFloat() / goal.targetDurationDays * 100).toInt().coerceIn(0, 100)
    } else 0

    Column(modifier = GlanceModifier.fillMaxWidth()) {
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(modifier = GlanceModifier.defaultWeight()) {
                Text(
                    text = "META",
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurfaceVariant,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "${goal.currentStreakDays} / ${goal.targetDurationDays}",
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Text(
                text = "$progressPercent%",
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        
        Spacer(modifier = GlanceModifier.height(8.dp))
        
        // Barra de progresso visual simplificada para MVP
        Text(
            text = "━".repeat((progressPercent / 5).coerceAtLeast(1)),
            style = TextStyle(color = GlanceTheme.colors.primary, fontWeight = FontWeight.Bold)
        )
    }
}

@androidx.glance.preview.ExperimentalGlancePreviewApi
@androidx.glance.preview.Preview
@Composable
fun StreakDaysWidgetPreview() {
    val sampleGoal = Goal(
        id = 1L,
        name = "Read 10 pages",
        targetDurationDays = 30,
        startDate = java.time.LocalDate.now().minusDays(5),
        currentStreakDays = 6,
        longestStreakDays = 12,
        isCompleted = false,
        pastStreaks = emptyList()
    )
    GlanceTheme {
        StreakDaysWidgetContent(mainGoal = sampleGoal)
    }
}

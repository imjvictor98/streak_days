package com.cvj.app.streakdays.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cvj.app.streakdays.core.domain.model.Goal
import com.cvj.app.streakdays.feature.dashboard.ui.components.GoalCard
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testGoalCardDisplaysCorrectly() {
        val goal = Goal(
            id = 1L,
            name = "No Sugar",
            targetDurationDays = 30,
            startDate = LocalDate.now().minusDays(5),
            currentStreakDays = 6,
            longestStreakDays = 6,
            isCompleted = false,
            pastStreaks = emptyList()
        )

        composeTestRule.setContent {
            GoalCard(isCompleted = goal.isCompleted, onClick = {}) {
                Header(
                    title = goal.name,
                    subtitle = "Keep going!",
                    isCompleted = goal.isCompleted,
                    modifier = androidx.compose.ui.Modifier
                )
                StatsArea(
                    currentStreak = goal.currentStreakDays,
                    bestStreak = goal.longestStreakDays,
                    target = goal.targetDurationDays,
                    isCompleted = goal.isCompleted,
                    modifier = androidx.compose.ui.Modifier
                )
            }
        }

        composeTestRule.onNodeWithText("No Sugar").assertExists()
        composeTestRule.onNodeWithText("6").assertExists()
        composeTestRule.onNodeWithText("30").assertExists()
    }
}

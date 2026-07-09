package com.assabloy.livvi.streakdays.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assabloy.livvi.streakdays.domain.model.Goal
import com.assabloy.livvi.streakdays.ui.dashboard.GoalCard
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
            GoalCard(goal = goal, onClick = {}, onLogRelapse = {})
        }

        composeTestRule.onNodeWithText("No Sugar").assertExists()
        composeTestRule.onNodeWithText("6").assertExists()
        composeTestRule.onNodeWithText("30").assertExists()
    }
}

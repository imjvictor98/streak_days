package com.cvj.app.streakdays.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cvj.app.streakdays.feature.create.ui.CreateGoalScreen
import com.cvj.app.streakdays.feature.dashboard.ui.DashboardScreen
import com.cvj.app.streakdays.feature.details.ui.GoalDetailsScreen

object NavDestinations {
    const val DASHBOARD = "dashboard"
    const val CREATE_GOAL = "create_goal"
    const val GOAL_DETAILS = "goal_details/{goalId}"
    fun goalDetails(goalId: Long) = "goal_details/$goalId"
}

@Composable
fun StreakDaysNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavDestinations.DASHBOARD
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavDestinations.DASHBOARD) {
            DashboardScreen(
                onNavigateToCreateGoal = {
                    navController.navigate(NavDestinations.CREATE_GOAL)
                },
                onNavigateToGoalDetails = { goalId ->
                    navController.navigate(NavDestinations.goalDetails(goalId))
                }
            )
        }
        
        composable(NavDestinations.CREATE_GOAL) {
            CreateGoalScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(NavDestinations.GOAL_DETAILS) {
            GoalDetailsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

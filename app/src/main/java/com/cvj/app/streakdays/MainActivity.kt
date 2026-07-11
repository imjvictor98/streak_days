package com.cvj.app.streakdays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cvj.app.streakdays.core.navigation.StreakDaysNavGraph
import com.cvj.app.streakdays.core.designsystem.theme.StreakDaysTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StreakDaysTheme {
                StreakDaysNavGraph()
            }
        }
    }
}
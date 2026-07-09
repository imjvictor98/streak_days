package com.assabloy.livvi.streakdays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.assabloy.livvi.streakdays.core.navigation.StreakDaysNavGraph
import com.assabloy.livvi.streakdays.core.designsystem.StreakDaysTheme
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
package com.cvj.app.streakdays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.cvj.app.streakdays.core.navigation.StreakDaysNavGraph
import com.cvj.app.streakdays.core.designsystem.theme.StreakDaysTheme
import com.cvj.app.streakdays.core.domain.widget.WidgetUpdater
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    @Inject
    lateinit var widgetUpdater: WidgetUpdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StreakDaysTheme {
                StreakDaysNavGraph()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            widgetUpdater.updateGoalsWidget()
        }
    }
}
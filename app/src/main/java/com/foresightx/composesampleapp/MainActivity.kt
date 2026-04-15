package com.foresightx.composesampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.foresightx.composesampleapp.core.designsystem.ComposeSampleTheme
import com.foresightx.composesampleapp.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

/**
 * 应用主入口 Activity。
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeSampleTheme {
                AppNavHost()
            }
        }
    }
}

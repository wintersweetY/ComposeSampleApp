package com.foresightx.composesampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.foresightx.composesampleapp.ui.AppRoot
import com.foresightx.composesampleapp.ui.theme.ComposeSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeSampleAppTheme {
                AppRoot()
            }
        }
    }
}

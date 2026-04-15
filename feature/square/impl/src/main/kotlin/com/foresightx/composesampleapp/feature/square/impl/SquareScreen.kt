package com.foresightx.composesampleapp.feature.square.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.foresightx.composesampleapp.core.ui.CenterMessage

/**
 * 广场页路由入口。
 */
@Composable
fun SquareRoute() {
    val vm: SquareViewModel = hiltViewModel()
    val uiState by vm.uiState.collectAsState()
    CenterMessage(title = uiState.title, subtitle = uiState.subtitle)
}

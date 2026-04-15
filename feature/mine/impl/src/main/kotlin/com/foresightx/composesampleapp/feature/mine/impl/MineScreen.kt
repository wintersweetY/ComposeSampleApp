package com.foresightx.composesampleapp.feature.mine.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.foresightx.composesampleapp.core.ui.CenterMessage

/**
 * 我的页路由入口。
 */
@Composable
fun MineRoute() {
    val vm: MineViewModel = hiltViewModel()
    val uiState by vm.uiState.collectAsState()
    CenterMessage(title = uiState.title, subtitle = uiState.subtitle)
}

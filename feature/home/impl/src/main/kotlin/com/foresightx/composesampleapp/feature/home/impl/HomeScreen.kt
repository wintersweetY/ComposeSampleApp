package com.foresightx.composesampleapp.feature.home.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.foresightx.composesampleapp.core.ui.CenterMessage

/**
 * 首页路由入口。
 */
@Composable
fun HomeRoute() {
    val vm: HomeViewModel = hiltViewModel()
    val uiState by vm.uiState.collectAsState()
    HomeScreen(uiState = uiState)
}

/**
 * 首页界面。
 *
 * @param uiState UI 状态。
 */
@Composable
private fun HomeScreen(uiState: HomeUiState) {
    CenterMessage(title = uiState.title, subtitle = uiState.subtitle)
}

/**
 * 首页预览。
 */
@Preview(showBackground = true, name = "Home")
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(
            title = "首页",
            subtitle = "首页内容建设中",
        ),
    )
}

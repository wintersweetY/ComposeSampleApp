package com.foresightx.composesampleapp.feature.square.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.foresightx.composesampleapp.core.ui.CenterMessage

/**
 * 广场页路由入口。
 */
@Composable
fun SquareRoute() {
    val vm: SquareViewModel = hiltViewModel()
    val uiState by vm.uiState.collectAsState()
    SquareScreen(uiState = uiState)
}

/**
 * 广场页界面。
 *
 * @param uiState UI 状态。
 */
@Composable
private fun SquareScreen(uiState: SquareUiState) {
    CenterMessage(title = uiState.title, subtitle = uiState.subtitle)
}

/**
 * 广场页预览。
 */
@Preview(showBackground = true, name = "Square")
@Composable
private fun SquareScreenPreview() {
    SquareScreen(
        uiState = SquareUiState(
            title = "广场",
            subtitle = "广场功能开发中",
        ),
    )
}

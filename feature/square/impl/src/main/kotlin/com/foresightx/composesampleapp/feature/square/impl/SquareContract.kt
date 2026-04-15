package com.foresightx.composesampleapp.feature.square.impl

/**
 * 广场页状态模型。
 *
 * @param title 标题。
 * @param subtitle 副标题。
 */
data class SquareUiState(
    val title: String = "\u5e7f\u573a",
    val subtitle: String = "\u5e7f\u573a\u529f\u80fd\u5f00\u53d1\u4e2d",
)

sealed interface SquareUiIntent {
    data object Load : SquareUiIntent
}

sealed interface SquareUiEffect {
    data class ShowToast(val message: String) : SquareUiEffect
}

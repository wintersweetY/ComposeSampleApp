package com.foresightx.composesampleapp.feature.mine.impl

/**
 * 我的页状态模型。
 *
 * @param title 标题。
 * @param subtitle 副标题。
 */
data class MineUiState(
    val title: String = "\u6211\u7684",
    val subtitle: String = "\u4e2a\u4eba\u4e2d\u5fc3\u5efa\u8bbe\u4e2d",
)

sealed interface MineUiIntent {
    data object Load : MineUiIntent
}

sealed interface MineUiEffect {
    data class ShowToast(val message: String) : MineUiEffect
}

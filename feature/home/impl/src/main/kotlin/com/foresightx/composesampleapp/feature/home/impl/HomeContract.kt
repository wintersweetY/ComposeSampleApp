package com.foresightx.composesampleapp.feature.home.impl

/**
 * 首页状态模型。
 *
 * @param title 标题。
 * @param subtitle 副标题。
 */
data class HomeUiState(
    val title: String = "\u9996\u9875",
    val subtitle: String = "\u9996\u9875\u5185\u5bb9\u5efa\u8bbe\u4e2d",
)

/**
 * 首页用户意图。
 */
sealed interface HomeUiIntent {
    data object Load : HomeUiIntent
}

/**
 * 首页一次性副作用。
 */
sealed interface HomeUiEffect {
    /**
     * 页面提示。
     *
     * @param message 提示内容。
     */
    data class ShowToast(val message: String) : HomeUiEffect
}

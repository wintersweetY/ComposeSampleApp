package com.foresightx.composesampleapp.feature.mine.impl

/**
 * 我的页状态模型。
 *
 * @param phone 手机号输入值。
 * @param code 验证码输入值。
 * @param isLoading 是否正在请求。
 * @param isSendingCode 是否正在发送验证码。
 * @param nickName 用户昵称。
 * @param userId 用户 ID。
 * @param statusMessage 状态提示信息。
 * @param errorMessage 错误信息。
 */
data class MineUiState(
    val phone: String = "",
    val code: String = "",
    val isLoading: Boolean = false,
    val isSendingCode: Boolean = false,
    val nickName: String = "未登录",
    val userId: Long? = null,
    val statusMessage: String? = null,
    val errorMessage: String? = null,
)

/**
 * 我的页用户意图。
 */
sealed interface MineUiIntent {
    /**
     * 更新手机号输入。
     *
     * @param phone 新手机号。
     */
    data class ChangePhone(val phone: String) : MineUiIntent

    /**
     * 更新验证码输入。
     *
     * @param code 新验证码。
     */
    data class ChangeCode(val code: String) : MineUiIntent

    /** 发送短信验证码。 */
    data object SendSmsCode : MineUiIntent

    /** 触发登录并加载用户信息。 */
    data object SubmitLogin : MineUiIntent
}

/**
 * 我的页一次性效果。
 */
sealed interface MineUiEffect {
    /**
     * 弹出提示。
     *
     * @param message 提示内容。
     */
    data class ShowToast(val message: String) : MineUiEffect
}

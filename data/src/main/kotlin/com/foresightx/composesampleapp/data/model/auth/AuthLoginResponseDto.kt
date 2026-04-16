package com.foresightx.composesampleapp.data.model.auth

/**
 * 手机验证码登录响应 DTO。
 *
 * @param userId 用户 ID。
 * @param token 鉴权 token。
 */
data class AuthLoginResponseDto(
    val userId: Long? = null,
    val token: String? = null,
)

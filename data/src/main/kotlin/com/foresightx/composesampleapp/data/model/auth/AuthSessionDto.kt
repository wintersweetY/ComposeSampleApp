package com.foresightx.composesampleapp.data.model.auth

/**
 * 本地登录会话 DTO。
 *
 * @param userId 用户 ID。
 * @param nickName 用户昵称。
 * @param token 业务鉴权 token。
 * @param mallToken 商城 token。
 * @param mallSessionId 商城 sessionId。
 */
data class AuthSessionDto(
    val userId: Long? = null,
    val nickName: String? = null,
    val token: String? = null,
    val mallToken: String? = null,
    val mallSessionId: String? = null,
)


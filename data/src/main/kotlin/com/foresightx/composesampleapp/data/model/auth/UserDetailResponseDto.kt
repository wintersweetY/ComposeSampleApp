package com.foresightx.composesampleapp.data.model.auth

/**
 * 用户详情响应 DTO。
 *
 * @param userId 用户 ID。
 * @param nickName 用户昵称。
 * @param image 用户头像。
 */
data class UserDetailResponseDto(
    val userId: Long? = null,
    val nickName: String? = null,
    val image: String? = null,
)

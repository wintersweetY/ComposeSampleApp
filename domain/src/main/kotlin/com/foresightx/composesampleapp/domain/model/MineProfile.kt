package com.foresightx.composesampleapp.domain.model

/**
 * 我的页用户信息领域模型。
 *
 * @param userId 用户 ID。
 * @param nickName 用户昵称。
 * @param avatar 用户头像地址。
 */
data class MineProfile(
    val userId: Long,
    val nickName: String,
    val avatar: String?,
)

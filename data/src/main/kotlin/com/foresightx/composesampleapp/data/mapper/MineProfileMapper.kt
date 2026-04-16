package com.foresightx.composesampleapp.data.mapper

import com.foresightx.composesampleapp.data.model.auth.UserDetailResponseDto
import com.foresightx.composesampleapp.domain.model.MineProfile

/**
 * 将用户详情 DTO 转换为领域模型。
 *
 * @receiver 用户详情 DTO。
 * @return 我的页用户信息领域模型。
 */
fun UserDetailResponseDto.toDomain(): MineProfile = MineProfile(
    userId = userId ?: 0L,
    nickName = nickName.orEmpty(),
    avatar = image,
)

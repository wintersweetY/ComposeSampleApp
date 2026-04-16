package com.foresightx.composesampleapp.data.model.auth

/**
 * 发送短信验证码请求 DTO。
 *
 * @param phone 手机号。
 * @param bizCode 业务编码。
 */
data class SmsSendRequestDto(
    val phone: String,
    val bizCode: String,
)

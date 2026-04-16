package com.foresightx.composesampleapp.data.model.auth

/**
 * 手机验证码登录请求 DTO。
 *
 * @param phone 手机号。
 * @param code 验证码。
 * @param invitationCode 邀请码。
 * @param invitationType 邀请类型。
 */
data class PhoneLoginRequestDto(
    val phone: String,
    val code: String,
    val invitationCode: String? = null,
    val invitationType: String? = null,
)

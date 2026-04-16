package com.foresightx.composesampleapp.data.remote.api

import com.foresightx.composesampleapp.core.network.ApiResultDto
import com.foresightx.composesampleapp.data.model.auth.AuthLoginResponseDto
import com.foresightx.composesampleapp.data.model.auth.PhoneLoginRequestDto
import com.foresightx.composesampleapp.data.model.auth.SmsSendRequestDto
import com.foresightx.composesampleapp.data.model.auth.UserDetailResponseDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * 认证与用户信息接口定义。
 */
interface AuthApiService {
    /**
     * 发送短信验证码。
     *
     * @param request 发送短信请求体。
     * @return 发送结果。
     */
    @POST("v1/sms/sendSmsVerify")
    suspend fun sendSmsVerify(
        @Body request: SmsSendRequestDto,
    ): ApiResultDto<Boolean>

    /**
     * 手机验证码登录。
     *
     * @param request 登录请求体。
     * @return 登录结果。
     */
    @POST("v1/auth/app/phone/login")
    suspend fun phoneLogin(
        @Body request: PhoneLoginRequestDto,
    ): ApiResultDto<AuthLoginResponseDto>

    /**
     * 查询当前登录用户信息。
     *
     * @param token 登录 token。
     * @return 用户详情结果。
     */
    @POST("v1/user/detail")
    suspend fun queryUserDetail(
        @Header("Authorization") token: String,
    ): ApiResultDto<UserDetailResponseDto>

    /**
     * 退出登录。
     *
     * @param token 登录 token。
     * @return 退出结果。
     */
    @POST("v1/user/logout/confirm")
    suspend fun logout(
        @Header("Authorization") token: String,
    ): ApiResultDto<Boolean>
}

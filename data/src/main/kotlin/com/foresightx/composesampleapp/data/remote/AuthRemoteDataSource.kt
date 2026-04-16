package com.foresightx.composesampleapp.data.remote

import com.foresightx.composesampleapp.data.model.auth.AuthLoginResponseDto
import com.foresightx.composesampleapp.data.model.auth.PhoneLoginRequestDto
import com.foresightx.composesampleapp.data.model.auth.SmsSendRequestDto
import com.foresightx.composesampleapp.data.model.auth.UserDetailResponseDto
import com.foresightx.composesampleapp.data.remote.api.AuthApiService
import javax.inject.Inject

/**
 * 认证与用户信息远端数据源。
 *
 * @param apiService 认证接口服务。
 */
class AuthRemoteDataSource @Inject constructor(
    private val apiService: AuthApiService,
) {
    /**
     * 发送登录短信验证码。
     *
     * @param phone 手机号。
     * @return true 表示发送成功。
     */
    suspend fun sendLoginSmsCode(phone: String): Boolean = apiService.sendSmsVerify(
        request = SmsSendRequestDto(
            phone = phone,
            bizCode = "foresightx_login",
        ),
    ).requireData()

    /**
     * 手机号验证码登录。
     *
     * @param phone 手机号。
     * @param code 验证码。
     * @return 登录响应数据。
     */
    suspend fun phoneLogin(
        phone: String,
        code: String,
    ): AuthLoginResponseDto = apiService.phoneLogin(
        request = PhoneLoginRequestDto(phone = phone, code = code),
    ).requireData()

    /**
     * 查询当前用户详情。
     *
     * @param token 登录 token。
     * @return 用户详情。
     */
    suspend fun queryUserDetail(token: String): UserDetailResponseDto =
        apiService.queryUserDetail(token).requireData()
}

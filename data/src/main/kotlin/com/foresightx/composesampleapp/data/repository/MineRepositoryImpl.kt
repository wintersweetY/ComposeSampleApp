package com.foresightx.composesampleapp.data.repository

import com.foresightx.composesampleapp.data.mapper.toDomain
import com.foresightx.composesampleapp.data.remote.AuthRemoteDataSource
import com.foresightx.composesampleapp.domain.model.MineProfile
import com.foresightx.composesampleapp.domain.repository.MineRepository
import javax.inject.Inject

/**
 * 我的页仓库实现。
 *
 * @param authRemoteDataSource 认证远端数据源。
 */
class MineRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : MineRepository {
    /**
     * 发送登录短信验证码。
     *
     * @param phone 手机号。
     * @return true 表示发送成功。
     */
    override suspend fun sendLoginSmsCode(phone: String): Boolean =
        authRemoteDataSource.sendLoginSmsCode(phone)

    /**
     * 登录后获取用户详情。
     *
     * @param phone 手机号。
     * @param code 验证码。
     * @return 我的页用户信息。
     */
    override suspend fun loginAndFetchProfile(phone: String, code: String): MineProfile {
        val loginResponse = authRemoteDataSource.phoneLogin(
            phone = phone,
            code = code,
        )
        val token = loginResponse.token ?: error("登录成功但未返回 token")
        return authRemoteDataSource.queryUserDetail(token).toDomain()
    }
}

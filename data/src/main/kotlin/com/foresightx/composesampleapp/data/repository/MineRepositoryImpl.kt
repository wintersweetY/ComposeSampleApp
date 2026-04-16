package com.foresightx.composesampleapp.data.repository

import com.foresightx.composesampleapp.data.mapper.toDomain
import com.foresightx.composesampleapp.data.model.auth.AuthSessionDto
import com.foresightx.composesampleapp.data.local.AuthLocalDataSource
import com.foresightx.composesampleapp.data.remote.AuthRemoteDataSource
import com.foresightx.composesampleapp.domain.model.MineProfile
import com.foresightx.composesampleapp.domain.repository.MineRepository
import javax.inject.Inject

/**
 * 我的页仓库实现。
 *
 * @param authRemoteDataSource 认证远端数据源。
 * @param authLocalDataSource 登录本地数据源。
 */
class MineRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
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
        val profile = authRemoteDataSource.queryUserDetail(token).toDomain()
        authLocalDataSource.saveSession(
            AuthSessionDto(
                userId = profile.userId,
                nickName = profile.nickName,
                token = token,
                mallToken = loginResponse.mallToken,
                mallSessionId = loginResponse.mallSessionId,
            ),
        )
        return profile
    }

    /**
     * 获取本地缓存用户信息。
     *
     * @return 已缓存的用户信息；未登录返回 null。
     */
    override suspend fun getLocalProfile(): MineProfile? {
        val session = authLocalDataSource.readSession() ?: return null
        val userId = session.userId ?: return null
        return MineProfile(
            userId = userId,
            nickName = session.nickName.orEmpty().ifBlank { "未命名用户" },
            avatar = null,
        )
    }

    /**
     * 退出登录并清理本地会话。
     *
     * @return true 表示退出成功（本地已清理）。
     */
    override suspend fun logout(): Boolean {
        val token = authLocalDataSource.readSession()?.token
        val remoteSuccess = if (token.isNullOrBlank()) {
            true
        } else {
            runCatching { authRemoteDataSource.logout(token) }.getOrDefault(false)
        }
        authLocalDataSource.clearSession()
        return remoteSuccess
    }
}

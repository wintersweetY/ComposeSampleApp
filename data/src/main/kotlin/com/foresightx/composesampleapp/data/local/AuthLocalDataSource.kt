package com.foresightx.composesampleapp.data.local

import android.content.Context
import com.foresightx.composesampleapp.data.model.auth.AuthSessionDto
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 登录会话本地数据源。
 *
 * @param context Application Context。
 */
@Singleton
class AuthLocalDataSource @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    /**
     * 保存登录会话。
     *
     * @param session 登录会话对象。
     * @return Unit。
     */
    fun saveSession(session: AuthSessionDto) {
        preferences.edit()
            .putLong(KEY_USER_ID, session.userId ?: 0L)
            .putString(KEY_NICK_NAME, session.nickName.orEmpty())
            .putString(KEY_TOKEN, session.token.orEmpty())
            .putString(KEY_MALL_TOKEN, session.mallToken.orEmpty())
            .putString(KEY_MALL_SESSION_ID, session.mallSessionId.orEmpty())
            .apply()
    }

    /**
     * 读取登录会话。
     *
     * @return 登录会话；未登录时返回 null。
     */
    fun readSession(): AuthSessionDto? {
        val token = preferences.getString(KEY_TOKEN, "").orEmpty()
        if (token.isBlank()) {
            return null
        }
        val userId = preferences.getLong(KEY_USER_ID, 0L).takeIf { it > 0L }
        val nickName = preferences.getString(KEY_NICK_NAME, "").orEmpty().ifBlank { null }
        val mallToken = preferences.getString(KEY_MALL_TOKEN, "").orEmpty().ifBlank { null }
        val mallSessionId = preferences.getString(KEY_MALL_SESSION_ID, "").orEmpty().ifBlank { null }
        return AuthSessionDto(
            userId = userId,
            nickName = nickName,
            token = token,
            mallToken = mallToken,
            mallSessionId = mallSessionId,
        )
    }

    /**
     * 清空登录会话。
     *
     * @return Unit。
     */
    fun clearSession() {
        preferences.edit().clear().apply()
    }

    private companion object {
        private const val PREF_FILE_NAME = "auth_session"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_NICK_NAME = "nick_name"
        private const val KEY_TOKEN = "token"
        private const val KEY_MALL_TOKEN = "mall_token"
        private const val KEY_MALL_SESSION_ID = "mall_session_id"
    }
}


package com.foresightx.composesampleapp.domain.repository

import com.foresightx.composesampleapp.domain.model.MineProfile

/**
 * 我的页数据仓库接口。
 */
interface MineRepository {
    /**
     * 发送登录短信验证码。
     *
     * @param phone 手机号。
     * @return true 表示发送成功。
     */
    suspend fun sendLoginSmsCode(phone: String): Boolean

    /**
     * 使用手机号验证码登录并查询我的页信息。
     *
     * @param phone 手机号。
     * @param code 验证码。
     * @return 我的页用户信息。
     */
    suspend fun loginAndFetchProfile(phone: String, code: String): MineProfile
}

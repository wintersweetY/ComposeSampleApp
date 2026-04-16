package com.foresightx.composesampleapp.domain.usecase

import com.foresightx.composesampleapp.domain.repository.MineRepository

/**
 * 发送登录短信验证码用例。
 *
 * @param repository 我的页仓库。
 */
class SendLoginSmsCodeUseCase(
    private val repository: MineRepository,
) {
    /**
     * 发送登录验证码。
     *
     * @param phone 手机号。
     * @return true 表示发送成功。
     */
    suspend operator fun invoke(phone: String): Boolean =
        repository.sendLoginSmsCode(phone)
}

package com.foresightx.composesampleapp.domain.usecase

import com.foresightx.composesampleapp.domain.model.MineProfile
import com.foresightx.composesampleapp.domain.repository.MineRepository

/**
 * 登录并获取我的页信息用例。
 *
 * @param repository 我的页仓库。
 */
class LoginAndFetchMineProfileUseCase(
    private val repository: MineRepository,
) {
    /**
     * 执行业务查询。
     *
     * @param phone 手机号。
     * @param code 验证码。
     * @return 我的页用户信息。
     */
    suspend operator fun invoke(phone: String, code: String): MineProfile =
        repository.loginAndFetchProfile(phone, code)
}

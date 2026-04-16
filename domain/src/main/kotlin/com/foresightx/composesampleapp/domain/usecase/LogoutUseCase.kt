package com.foresightx.composesampleapp.domain.usecase

import com.foresightx.composesampleapp.domain.repository.MineRepository

/**
 * 退出登录用例。
 *
 * @param repository 我的页仓库。
 */
class LogoutUseCase(
    private val repository: MineRepository,
) {
    /**
     * 执行退出登录。
     *
     * @return true 表示退出成功（本地已清理）。
     */
    suspend operator fun invoke(): Boolean = repository.logout()
}


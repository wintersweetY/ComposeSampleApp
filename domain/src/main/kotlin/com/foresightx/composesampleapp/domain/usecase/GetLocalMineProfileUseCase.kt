package com.foresightx.composesampleapp.domain.usecase

import com.foresightx.composesampleapp.domain.model.MineProfile
import com.foresightx.composesampleapp.domain.repository.MineRepository

/**
 * 获取本地登录用户信息用例。
 *
 * @param repository 我的页仓库。
 */
class GetLocalMineProfileUseCase(
    private val repository: MineRepository,
) {
    /**
     * 获取本地登录信息。
     *
     * @return 本地用户信息；未登录返回 null。
     */
    suspend operator fun invoke(): MineProfile? = repository.getLocalProfile()
}


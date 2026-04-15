package com.foresightx.composesampleapp.domain.usecase

import com.foresightx.composesampleapp.domain.model.TabContent
import com.foresightx.composesampleapp.domain.repository.MineRepository

/**
 * 获取我的页数据用例。
 *
 * @param repository 我的页仓库。
 */
class GetMineTabContentUseCase(
    private val repository: MineRepository,
) {
    /**
     * 执行业务查询。
     *
     * @return 我的页展示内容。
     */
    suspend operator fun invoke(): TabContent = repository.getTabContent()
}

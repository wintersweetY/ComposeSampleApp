package com.foresightx.composesampleapp.domain.usecase

import com.foresightx.composesampleapp.domain.model.TabContent
import com.foresightx.composesampleapp.domain.repository.SquareRepository

/**
 * 获取广场数据用例。
 *
 * @param repository 广场仓库。
 */
class GetSquareTabContentUseCase(
    private val repository: SquareRepository,
) {
    /**
     * 执行业务查询。
     *
     * @return 广场展示内容。
     */
    suspend operator fun invoke(): TabContent = repository.getTabContent()
}

package com.foresightx.composesampleapp.domain.usecase

import com.foresightx.composesampleapp.domain.model.TabContent
import com.foresightx.composesampleapp.domain.repository.HomeRepository

/**
 * 获取首页数据用例。
 *
 * @param repository 首页仓库。
 */
class GetHomeTabContentUseCase(
    private val repository: HomeRepository,
) {
    /**
     * 执行业务查询。
     *
     * @return 首页展示内容。
     */
    suspend operator fun invoke(): TabContent = repository.getTabContent()
}

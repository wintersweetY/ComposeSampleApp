package com.foresightx.composesampleapp.domain.repository

import com.foresightx.composesampleapp.domain.model.TabContent

/**
 * 首页数据仓库接口。
 */
interface HomeRepository {
    /**
     * 获取首页展示数据。
     *
     * @return 首页展示内容。
     */
    suspend fun getTabContent(): TabContent
}

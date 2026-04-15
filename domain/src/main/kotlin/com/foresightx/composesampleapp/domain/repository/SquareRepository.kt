package com.foresightx.composesampleapp.domain.repository

import com.foresightx.composesampleapp.domain.model.TabContent

/**
 * 广场页数据仓库接口。
 */
interface SquareRepository {
    /**
     * 获取广场页展示数据。
     *
     * @return 广场页展示内容。
     */
    suspend fun getTabContent(): TabContent
}

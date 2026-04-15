package com.foresightx.composesampleapp.domain.repository

import com.foresightx.composesampleapp.domain.model.TabContent

/**
 * 我的页数据仓库接口。
 */
interface MineRepository {
    /**
     * 获取我的页展示数据。
     *
     * @return 我的页展示内容。
     */
    suspend fun getTabContent(): TabContent
}

package com.foresightx.composesampleapp.data.local

import com.foresightx.composesampleapp.data.model.TabContentDto

/**
 * 本地数据源占位实现。
 */
class TabLocalDataSource {
    /** @return 首页本地缓存，当前返回 null。 */
    suspend fun getHomeFallback(): TabContentDto? = null

    /** @return 广场页本地缓存，当前返回 null。 */
    suspend fun getSquareFallback(): TabContentDto? = null

    /** @return 我的页本地缓存，当前返回 null。 */
    suspend fun getMineFallback(): TabContentDto? = null
}

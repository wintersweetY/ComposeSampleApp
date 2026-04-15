package com.foresightx.composesampleapp.data.remote

import com.foresightx.composesampleapp.data.model.TabContentDto

/**
 * 远端数据源占位实现。
 */
class TabRemoteDataSource {
    /**
     * 获取首页远端内容。
     *
     * @return 首页内容 DTO。
     */
    suspend fun getHomeContent(): TabContentDto = TabContentDto(
        title = "\u9996\u9875",
        subtitle = "\u9996\u9875\u5185\u5bb9\u5efa\u8bbe\u4e2d",
    )

    /**
     * 获取广场远端内容。
     *
     * @return 广场内容 DTO。
     */
    suspend fun getSquareContent(): TabContentDto = TabContentDto(
        title = "\u5e7f\u573a",
        subtitle = "\u5e7f\u573a\u529f\u80fd\u5f00\u53d1\u4e2d",
    )

    /**
     * 获取我的页远端内容。
     *
     * @return 我的页内容 DTO。
     */
    suspend fun getMineContent(): TabContentDto = TabContentDto(
        title = "\u6211\u7684",
        subtitle = "\u4e2a\u4eba\u4e2d\u5fc3\u5efa\u8bbe\u4e2d",
    )
}

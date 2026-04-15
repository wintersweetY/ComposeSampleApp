package com.foresightx.composesampleapp.data.repository

import com.foresightx.composesampleapp.data.local.TabLocalDataSource
import com.foresightx.composesampleapp.data.mapper.toDomain
import com.foresightx.composesampleapp.data.remote.TabRemoteDataSource
import com.foresightx.composesampleapp.domain.model.TabContent
import com.foresightx.composesampleapp.domain.repository.HomeRepository
import javax.inject.Inject

/**
 * 首页仓库实现。
 *
 * @param remoteDataSource 远端数据源。
 * @param localDataSource 本地数据源。
 */
class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: TabRemoteDataSource,
    private val localDataSource: TabLocalDataSource,
) : HomeRepository {
    /**
     * 优先读取本地缓存，若为空则读取远端。
     *
     * @return 首页领域模型。
     */
    override suspend fun getTabContent(): TabContent {
        val local = localDataSource.getHomeFallback()
        return (local ?: remoteDataSource.getHomeContent()).toDomain()
    }
}

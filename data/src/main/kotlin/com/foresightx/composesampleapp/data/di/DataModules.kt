package com.foresightx.composesampleapp.data.di

import com.foresightx.composesampleapp.data.local.TabLocalDataSource
import com.foresightx.composesampleapp.data.remote.TabRemoteDataSource
import com.foresightx.composesampleapp.data.repository.HomeRepositoryImpl
import com.foresightx.composesampleapp.data.repository.MineRepositoryImpl
import com.foresightx.composesampleapp.data.repository.SquareRepositoryImpl
import com.foresightx.composesampleapp.domain.repository.HomeRepository
import com.foresightx.composesampleapp.domain.repository.MineRepository
import com.foresightx.composesampleapp.domain.repository.SquareRepository
import com.foresightx.composesampleapp.domain.usecase.GetHomeTabContentUseCase
import com.foresightx.composesampleapp.domain.usecase.GetMineTabContentUseCase
import com.foresightx.composesampleapp.domain.usecase.GetSquareTabContentUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Data 层对象提供模块。
 */
@Module
@InstallIn(SingletonComponent::class)
object DataProvideModule {
    /** @return 远端数据源实例。 */
    @Provides
    @Singleton
    fun provideTabRemoteDataSource(): TabRemoteDataSource = TabRemoteDataSource()

    /** @return 本地数据源实例。 */
    @Provides
    @Singleton
    fun provideTabLocalDataSource(): TabLocalDataSource = TabLocalDataSource()

    /** @return 首页用例。 */
    @Provides
    @Singleton
    fun provideHomeUseCase(repository: HomeRepository): GetHomeTabContentUseCase =
        GetHomeTabContentUseCase(repository)

    /** @return 广场用例。 */
    @Provides
    @Singleton
    fun provideSquareUseCase(repository: SquareRepository): GetSquareTabContentUseCase =
        GetSquareTabContentUseCase(repository)

    /** @return 我的用例。 */
    @Provides
    @Singleton
    fun provideMineUseCase(repository: MineRepository): GetMineTabContentUseCase =
        GetMineTabContentUseCase(repository)
}

/**
 * Data 层接口绑定模块。
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {
    /**
     * 绑定首页仓库实现。
     *
     * @param impl 首页仓库实现。
     * @return 首页仓库接口。
     */
    @Binds
    @Singleton
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    /**
     * 绑定广场仓库实现。
     *
     * @param impl 广场仓库实现。
     * @return 广场仓库接口。
     */
    @Binds
    @Singleton
    abstract fun bindSquareRepository(impl: SquareRepositoryImpl): SquareRepository

    /**
     * 绑定我的仓库实现。
     *
     * @param impl 我的仓库实现。
     * @return 我的仓库接口。
     */
    @Binds
    @Singleton
    abstract fun bindMineRepository(impl: MineRepositoryImpl): MineRepository
}

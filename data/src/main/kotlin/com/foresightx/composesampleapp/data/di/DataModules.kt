package com.foresightx.composesampleapp.data.di

import com.foresightx.composesampleapp.core.common.ApiRuntimeConfig
import com.foresightx.composesampleapp.core.network.NetworkClient
import com.foresightx.composesampleapp.data.local.TabLocalDataSource
import com.foresightx.composesampleapp.data.remote.TabRemoteDataSource
import com.foresightx.composesampleapp.data.remote.api.AuthApiService
import com.foresightx.composesampleapp.data.remote.interceptor.ApiHeadersInterceptor
import com.foresightx.composesampleapp.data.repository.HomeRepositoryImpl
import com.foresightx.composesampleapp.data.repository.MineRepositoryImpl
import com.foresightx.composesampleapp.data.repository.SquareRepositoryImpl
import com.foresightx.composesampleapp.domain.repository.HomeRepository
import com.foresightx.composesampleapp.domain.repository.MineRepository
import com.foresightx.composesampleapp.domain.repository.SquareRepository
import com.foresightx.composesampleapp.domain.usecase.GetHomeTabContentUseCase
import com.foresightx.composesampleapp.domain.usecase.GetSquareTabContentUseCase
import com.foresightx.composesampleapp.domain.usecase.LoginAndFetchMineProfileUseCase
import com.foresightx.composesampleapp.domain.usecase.SendLoginSmsCodeUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import retrofit2.Retrofit

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

    /**
     * 提供 OkHttpClient。
     *
     * @param runtimeConfig 接口运行时配置。
     * @return OkHttpClient 实例。
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(runtimeConfig: ApiRuntimeConfig): OkHttpClient =
        NetworkClient.createOkHttpClient(
            enableLog = runtimeConfig.enableHttpLog(),
            interceptors = listOf<Interceptor>(ApiHeadersInterceptor(runtimeConfig)),
        )

    /**
     * 提供 Retrofit。
     *
     * @param runtimeConfig 接口运行时配置。
     * @param okHttpClient OkHttp 客户端。
     * @return Retrofit 实例。
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        runtimeConfig: ApiRuntimeConfig,
        okHttpClient: OkHttpClient,
    ): Retrofit = NetworkClient.createRetrofit(runtimeConfig.baseUrl(), okHttpClient)

    /**
     * 提供认证接口服务。
     *
     * @param retrofit Retrofit 实例。
     * @return 认证接口服务。
     */
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

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

    /** @return 我的页登录与详情查询用例。 */
    @Provides
    @Singleton
    fun provideMineUseCase(repository: MineRepository): LoginAndFetchMineProfileUseCase =
        LoginAndFetchMineProfileUseCase(repository)

    /** @return 发送登录短信验证码用例。 */
    @Provides
    @Singleton
    fun provideSendLoginSmsCodeUseCase(repository: MineRepository): SendLoginSmsCodeUseCase =
        SendLoginSmsCodeUseCase(repository)
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

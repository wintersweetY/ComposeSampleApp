package com.foresightx.composesampleapp.di

import android.content.Context
import android.provider.Settings
import com.foresightx.composesampleapp.BuildConfig
import com.foresightx.composesampleapp.core.common.ApiRuntimeConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * App 层运行时配置模块。
 */
@Module
@InstallIn(SingletonComponent::class)
object AppConfigModule {
    /**
     * 提供接口运行时配置。
     *
     * @param context Application Context。
     * @return 运行时配置实现。
     */
    @Provides
    @Singleton
    fun provideApiRuntimeConfig(
        @ApplicationContext context: Context,
    ): ApiRuntimeConfig = object : ApiRuntimeConfig {
        override fun baseUrl(): String = BuildConfig.BASE_URL

        override fun os(): String = "1"

        override fun channel(): String = BuildConfig.CHANNEL

        override fun deviceId(): String = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID,
        ) ?: "unknown-device"

        override fun appVersion(): String = BuildConfig.VERSION_NAME

        override fun enableHttpLog(): Boolean = BuildConfig.DEBUG
    }
}

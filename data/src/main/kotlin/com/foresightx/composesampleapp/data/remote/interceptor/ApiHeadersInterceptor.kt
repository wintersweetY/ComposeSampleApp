package com.foresightx.composesampleapp.data.remote.interceptor

import com.foresightx.composesampleapp.core.common.ApiRuntimeConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 通用请求头拦截器。
 *
 * 说明：
 * 1. 对所有请求统一补充基础请求头，避免散落在每个接口方法里。
 * 2. 登录态相关请求头（如 Authorization）仍由具体业务在接口层显式传入。
 *
 * @param runtimeConfig 运行时配置。
 */
@Singleton
class ApiHeadersInterceptor @Inject constructor(
    private val runtimeConfig: ApiRuntimeConfig,
) : Interceptor {
    /**
     * 拦截并补充通用请求头。
     *
     * @param chain 请求链。
     * @return 服务端响应。
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Content-Type", "application/json")
            .header("os", runtimeConfig.os())
            .header("channel", runtimeConfig.channel())
            .header("deviceId", runtimeConfig.deviceId())
            .header("appVersion", runtimeConfig.appVersion())
            .build()
        return chain.proceed(request)
    }
}


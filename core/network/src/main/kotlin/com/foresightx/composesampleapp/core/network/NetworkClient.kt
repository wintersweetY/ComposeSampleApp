package com.foresightx.composesampleapp.core.network

import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit 网络客户端工厂。
 */
object NetworkClient {
    /**
     * 创建 OkHttpClient。
     *
     * @param enableLog 是否开启请求日志。
     * @param interceptors 业务拦截器列表，会按传入顺序注册。
     * @return OkHttpClient 实例。
     */
    fun createOkHttpClient(
        enableLog: Boolean,
        interceptors: List<Interceptor> = emptyList(),
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        interceptors.forEach(builder::addInterceptor)
        if (enableLog) {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(logging)
        }
        return builder.build()
    }

    /**
     * 创建 Retrofit 实例。
     *
     * @param baseUrl 接口基础地址。
     * @param okHttpClient OkHttp 客户端。
     * @return Retrofit 实例。
     */
    fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

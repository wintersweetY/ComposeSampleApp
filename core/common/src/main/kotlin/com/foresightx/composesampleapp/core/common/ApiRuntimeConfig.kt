package com.foresightx.composesampleapp.core.common

/**
 * 接口运行时配置。
 *
 * 说明：
 * 1. 由入口层（app）提供具体值。
 * 2. data 层仅依赖该抽象，不直接依赖 app BuildConfig。
 */
interface ApiRuntimeConfig {
    /**
     * 获取接口基础地址。
     *
     * @return 完整基础地址，必须以 / 结尾。
     */
    fun baseUrl(): String

    /**
     * 获取请求头 os 参数。
     *
     * @return 设备类型编码。
     */
    fun os(): String

    /**
     * 获取请求头 channel 参数。
     *
     * @return 渠道标识。
     */
    fun channel(): String

    /**
     * 获取请求头 deviceId 参数。
     *
     * @return 设备唯一标识。
     */
    fun deviceId(): String

    /**
     * 获取请求头 appVersion 参数。
     *
     * @return 应用版本号。
     */
    fun appVersion(): String

    /**
     * 是否开启网络日志。
     *
     * @return true 表示开启日志拦截输出。
     */
    fun enableHttpLog(): Boolean
}


package com.foresightx.composesampleapp.core.common

/**
 * 统一结果模型。
 *
 * @param T 成功场景下返回的数据类型。
 */
sealed interface AppResult<out T> {
    /**
     * 表示请求成功。
     *
     * @param data 成功返回的数据。
     */
    data class Success<T>(val data: T) : AppResult<T>

    /**
     * 表示请求失败。
     *
     * @param message 失败信息。
     * @param cause 可选异常对象，便于日志定位。
     */
    data class Error(val message: String, val cause: Throwable? = null) : AppResult<Nothing>
}

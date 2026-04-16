package com.foresightx.composesampleapp.core.network

/**
 * 接口标准响应结构。
 *
 * @param T data 字段的业务类型。
 * @param data 响应业务数据。
 * @param code 业务状态码。
 * @param message 业务提示信息。
 */
data class ApiResultDto<T>(
    val data: T? = null,
    val code: String? = null,
    val message: String? = null,
) {
    /**
     * 成功状态码集合。
     */
    private val successCodes = setOf("0", "200", "00000")

    /**
     * 校验业务响应是否成功。
     *
     * @return 当前响应对象，用于链式调用。
     */
    fun requireSuccess(): ApiResultDto<T> {
        val responseCode = code?.trim()
        if (!responseCode.isNullOrEmpty() && responseCode !in successCodes) {
            error(message ?: "接口请求失败，code=$responseCode")
        }
        return this
    }

    /**
     * 提取业务数据，不存在时抛出异常。
     *
     * @return 非空业务数据。
     */
    fun requireData(): T = requireSuccess().data ?: error(message ?: "接口未返回业务数据")
}

package com.foresightx.composesampleapp.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * 协程调度器抽象。
 */
interface AppDispatchers {
    /** IO 调度器。 */
    val io: CoroutineDispatcher

    /** Main 调度器。 */
    val main: CoroutineDispatcher

    /** Default 调度器。 */
    val default: CoroutineDispatcher
}

/**
 * 默认调度器实现。
 */
object DefaultAppDispatchers : AppDispatchers {
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val default: CoroutineDispatcher = Dispatchers.Default
}

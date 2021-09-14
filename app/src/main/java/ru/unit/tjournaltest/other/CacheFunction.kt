package ru.unit.barsdiary.other

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CacheFunction<T>(private val func: Function<T>) {
    private val mutex = Mutex()
    private val cachedData = mutableMapOf<Int, T>()

    private suspend fun run(vararg args: Any): T {
        val hash = args.contentDeepHashCode()
        mutex.withLock {
            return cachedData.getOrPut(hash, { func.invoke(args) })
        }
    }

    suspend operator fun invoke(vararg args: Any): T {
        return run(*args)
    }

    suspend fun reset() {
        mutex.withLock {
            cachedData.clear()
        }
    }

    fun interface Function<T> {
        suspend fun invoke(args: Array<out Any>): T
    }
}
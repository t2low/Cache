package com.tappli.cachesample.library.cache

open class MergeCache<KEY : Any, VALUE : Any>(private val mainCache: Cache<KEY, VALUE>, private vararg val subCaches: Writable<KEY, VALUE>) : Cache<KEY, VALUE> {
    override suspend fun read(key: KEY): VALUE? {
        return mainCache.read(key)
    }

    override suspend fun write(key: KEY, value: VALUE?) {
        mainCache.write(key, value)
        subCaches.forEach { it.write(key, value) }
    }
}
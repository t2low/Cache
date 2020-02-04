package com.tappli.cachesample.library.cache

open class MergeCache<KEY : Any, VALUE : Any>(private val mainCache: Cache<KEY, VALUE>, private vararg val subCaches: Savable<KEY, VALUE>) : Cache<KEY, VALUE> {
    override suspend fun load(key: KEY): VALUE? {
        return mainCache.load(key)
    }

    override suspend fun save(key: KEY, value: VALUE?) {
        mainCache.save(key, value)
        subCaches.forEach { it.save(key, value) }
    }
}
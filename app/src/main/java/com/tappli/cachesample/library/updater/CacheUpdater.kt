package com.tappli.cachesample.library.updater

import com.tappli.cachesample.library.cache.Cache
import com.tappli.cachesample.library.source.Source

open class CacheUpdater<KEY : Any, VALUE : Any>(
    private val source: Source<KEY, VALUE>,
    private val cache: Cache<KEY, VALUE>
) {

    suspend fun update(key: KEY) {
        cache.read(key) ?: source.get(key).also {
            cache.write(key, it)
        }
    }
}
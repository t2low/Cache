package com.tappli.cachesample.library.cache

import com.tappli.cachesample.library.source.Source

open class FetchableCache<KEY : Any, VALUE : Any>(
    private val source: Source<KEY, VALUE>,
    private val cache: Cache<KEY, VALUE>
) : Cache<KEY, VALUE> {

    override suspend fun read(key: KEY): VALUE? {
        return cache.read(key) ?: source.get(key).also {
            cache.write(key, it)
        }
    }

    override suspend fun write(key: KEY, value: VALUE?) {
        cache.write(key, value)
    }
}
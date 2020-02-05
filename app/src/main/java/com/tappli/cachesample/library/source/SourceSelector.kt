package com.tappli.cachesample.library.source

import com.tappli.cachesample.library.cache.Cache

open class SourceSelector<KEY : Any, VALUE : Any>(
    private val source: Source<KEY, VALUE>,
    private val cache: Cache<KEY, VALUE>
) : Source<KEY, VALUE> {

    override suspend fun get(key: KEY): VALUE {
        return cache.read(key) ?: source.get(key).also {
            cache.write(key, it)
        }
    }
}
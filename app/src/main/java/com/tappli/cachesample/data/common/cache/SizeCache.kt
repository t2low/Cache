package com.tappli.cachesample.data.common.cache

import android.util.LruCache
import com.tappli.cachesample.library.cache.Cache

class SizeCache<KEY : Any, VALUE : Any>(maxSize: Int) : Cache<KEY, VALUE> {

    private val cache = LruCache<KEY, VALUE>(maxSize)

    override suspend fun read(key: KEY): VALUE? {
        return cache.get(key)
    }

    override suspend fun write(key: KEY, value: VALUE?) {
        if (value == null) {
            cache.remove(key)
        } else {
            cache.put(key, value)
        }
    }
}
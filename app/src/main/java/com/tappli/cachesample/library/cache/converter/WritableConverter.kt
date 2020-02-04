package com.tappli.cachesample.library.cache.converter

import com.tappli.cachesample.library.cache.Writable

open class WritableConverter<KEY : Any, IN : Any, OUT : Any>(
    private val writable: Writable<KEY, OUT>,
    private val converter: (IN?) -> OUT?
) : Writable<KEY, IN> {

    override suspend fun write(key: KEY, value: IN?) {
        writable.write(key, converter(value))
    }
}


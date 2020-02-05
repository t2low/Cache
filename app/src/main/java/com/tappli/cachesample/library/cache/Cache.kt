package com.tappli.cachesample.library.cache

interface Cache<KEY : Any, VALUE : Any> : Readable<KEY, VALUE>, Writable<KEY, VALUE> {
    override suspend fun read(key: KEY): VALUE?
    override suspend fun write(key: KEY, value: VALUE?)
}
package com.tappli.cachesample.library.cache

interface Readable<KEY : Any, VALUE : Any> {
    suspend fun read(key: KEY): VALUE?
}
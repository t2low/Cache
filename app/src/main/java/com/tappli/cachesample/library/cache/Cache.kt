package com.tappli.cachesample.library.cache

interface Cache<KEY : Any, VALUE : Any> {
    suspend fun load(key: KEY): VALUE?
    suspend fun save(key: KEY, value: VALUE?)
}
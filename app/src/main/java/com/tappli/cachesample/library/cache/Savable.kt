package com.tappli.cachesample.library.cache

interface Savable<KEY : Any, VALUE : Any> {
    suspend fun save(key: KEY, value: VALUE?)
}
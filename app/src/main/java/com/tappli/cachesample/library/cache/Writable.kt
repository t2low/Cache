package com.tappli.cachesample.library.cache

interface Writable<KEY : Any, VALUE : Any> {
    suspend fun write(key: KEY, value: VALUE?)
}
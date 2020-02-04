package com.tappli.cachesample.library.source

interface Source<KEY : Any, VALUE : Any> {
    suspend fun get(key: KEY): VALUE
}
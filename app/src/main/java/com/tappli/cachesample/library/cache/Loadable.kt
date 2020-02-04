package com.tappli.cachesample.library.cache

interface Loadable<KEY : Any, VALUE : Any> {
    suspend fun load(key: KEY): VALUE?
}
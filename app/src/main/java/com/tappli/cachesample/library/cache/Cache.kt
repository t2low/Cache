package com.tappli.cachesample.library.cache

interface Cache<KEY : Any, VALUE : Any> : Loadable<KEY, VALUE>, Savable<KEY, VALUE> {
    override suspend fun load(key: KEY): VALUE?
    override suspend fun save(key: KEY, value: VALUE?)
}
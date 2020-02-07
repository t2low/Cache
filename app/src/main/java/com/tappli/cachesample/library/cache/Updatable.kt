package com.tappli.cachesample.library.cache

interface Updatable<KEY : Any, VALUE : Any> {
    suspend fun update(key: KEY, updater: (VALUE?) -> VALUE?)
}

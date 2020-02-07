package com.tappli.cachesample.library.cache

interface Cache<KEY : Any, VALUE : Any> : Readable<KEY, VALUE>, Writable<KEY, VALUE>, Updatable<KEY, VALUE> {
    override suspend fun read(key: KEY): VALUE?
    override suspend fun write(key: KEY, value: VALUE?)
    override suspend fun update(key: KEY, updater: (VALUE?) -> VALUE?) {
        write(key, updater(read(key)))
    }
}
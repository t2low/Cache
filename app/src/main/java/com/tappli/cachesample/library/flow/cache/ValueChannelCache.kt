package com.tappli.cachesample.library.flow.cache

import com.tappli.cachesample.library.cache.Cache
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.mapNotNull

open class ValueChannelCache<KEY : Any, VALUE : Any>(
    private val cache: Cache<KEY, VALUE>
) : FlowAccessorCache<KEY, VALUE> {

    private val channels = mutableMapOf<KEY, ConflatedBroadcastChannel<KEY>>()

    override suspend fun read(key: KEY): VALUE? {
        return cache.read(key)
    }

    override suspend fun write(key: KEY, value: VALUE?) {
        cache.write(key, value)
        getChannel(key).send(key)
    }

    override suspend fun getFlow(key: KEY): Flow<VALUE> {
        return getChannel(key).asFlow().mapNotNull { cache.read(key) }
    }

    private fun getChannel(key: KEY): ConflatedBroadcastChannel<KEY> {
        return channels[key] ?: ConflatedBroadcastChannel(key).also {
            channels[key] = it
        }
    }
}
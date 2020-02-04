package com.tappli.cachesample.data

import com.tappli.cachesample.library.cache.Cache
import com.tappli.cachesample.library.flow.FlowAccessor
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.mapNotNull

class ChannelCache<KEY : Any, VALUE : Any>(
    private val cache: Cache<KEY, VALUE>
) : Cache<KEY, VALUE>, FlowAccessor<KEY, VALUE> {

    private val channels = mutableMapOf<KEY, ConflatedBroadcastChannel<KEY>>()

    override suspend fun load(key: KEY): VALUE? {
        return cache.load(key)
    }

    override suspend fun save(key: KEY, value: VALUE?) {
        cache.save(key, value)
        getChannel(key).send(key)
    }

    override suspend fun getFlow(key: KEY): Flow<VALUE> {
        return getChannel(key).asFlow().mapNotNull { cache.load(key) }
    }

    private fun getChannel(key: KEY): ConflatedBroadcastChannel<KEY> {
        return channels[key] ?: ConflatedBroadcastChannel(key).also {
            channels[key] = it
        }
    }
}
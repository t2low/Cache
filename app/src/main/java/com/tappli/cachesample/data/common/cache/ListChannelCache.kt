package com.tappli.cachesample.data.common.cache

import com.tappli.cachesample.library.cache.Cache
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.mapNotNull

abstract class ListChannelCache<KEY : Any, VALUE : Any>(private val getKeyFunc: (VALUE) -> KEY) {

    private val idListChannel = ConflatedBroadcastChannel<List<KEY>>(emptyList())
    private val valueCache = mutableMapOf<KEY, VALUE>()

    suspend fun add(values: List<VALUE>) {
        val newIdList = (idListChannel.value + values.map(getKeyFunc)).distinct()
        values.forEach {
            valueCache[getKeyFunc(it)] = it
        }
        idListChannel.send(newIdList)
    }

    suspend fun clear() {
        idListChannel.send(emptyList())
        valueCache.clear()
    }

    fun getList(): List<VALUE> {
        return idListChannel.value
            .mapNotNull { valueCache[it] }
    }

    suspend fun getValueCache(): Cache<KEY, VALUE> {
        return object : Cache<KEY, VALUE> {
            override suspend fun read(key: KEY): VALUE? {
                return valueCache[key]
            }

            override suspend fun write(key: KEY, value: VALUE?) {
                valueCache[key]
                if (idListChannel.value.contains(key)) {
                    idListChannel.send(idListChannel.value) // NOTE: IDリストの更新をすることで全体に通知
                }
            }
        }
    }

    fun getListFlow(): Flow<List<VALUE>> {
        return idListChannel
            .asFlow()
            .mapNotNull { it.mapNotNull { key -> valueCache[key] } }
    }

    fun getValueFlow(key: KEY): Flow<VALUE> {
        return idListChannel
            .asFlow()
            .mapNotNull { valueCache[key] }
    }
}

package com.tappli.cachesample.library.flow.cache

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.mapNotNull

open class ListChannelCache<KEY : Any, VALUE : Any>(private val getKeyFunc: (VALUE) -> KEY) {

    private val idListChannel = ConflatedBroadcastChannel<List<KEY>>(emptyList())
    private val valueCache = mutableMapOf<KEY, VALUE?>()

    val valueChannelCache: FlowAccessorCache<KEY, VALUE> =
        object : FlowAccessorCache<KEY, VALUE> {
            override suspend fun read(key: KEY): VALUE? {
                return valueCache[key]
            }

            override suspend fun write(key: KEY, value: VALUE?) {
                valueCache[key] = value
                if (idListChannel.value.contains(key)) {
                    idListChannel.send(idListChannel.value) // NOTE: IDリストの更新をすることで全体に通知
                }
            }

            override suspend fun getFlow(key: KEY): Flow<VALUE> {
                return idListChannel
                    .asFlow()
                    .mapNotNull { valueCache[key] }
            }
        }

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

    fun getListFlow(): Flow<List<VALUE>> {
        return idListChannel
            .asFlow()
            .mapNotNull { it.mapNotNull { key -> valueCache[key] } }
    }
}

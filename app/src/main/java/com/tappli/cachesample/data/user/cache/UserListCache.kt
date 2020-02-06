package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.data.common.cache.SizeCache
import com.tappli.cachesample.domain.user.model.User
import com.tappli.cachesample.library.cache.ListCache
import com.tappli.cachesample.library.flow.FlowAccessor
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

object UserListCache : ListCache<Int, Int, User>, FlowAccessor<Unit, List<User>> {
    private var cachedLastPage: Int = 0

    private val idListChannel = ConflatedBroadcastChannel<List<Int>>(emptyList())
    private val userCache = SizeCache<Int, User>(1000)

    override suspend fun read(key: Int): List<User>? {
        val diff = key - cachedLastPage
        if (diff < 0) {
            clear()
            return null
        }
        val users = idListChannel.value.mapNotNull { userCache.read(it) }
        return if (users.isNotEmpty()) users else null
    }

    override suspend fun write(key: Int, value: List<User>?) {
        val diff = key - cachedLastPage
        if (diff <= 0) {
            clear()
        }
        val newIds = value?.map { it.id.value } ?: emptyList()
        value?.forEach {
            userCache.write(it.id.value, it)
        }
        idListChannel.send(idListChannel.value + newIds)
        cachedLastPage = key
    }

    private suspend fun clear() {
        idListChannel.value.forEach { userCache.write(it, null) }
        idListChannel.send(emptyList())
        cachedLastPage = 0
    }

    override suspend fun getFlow(key: Unit): Flow<List<User>> {
        return idListChannel
            .asFlow()
            .filterNotNull()
            .filter { it.isNotEmpty() }
            .map { it.mapNotNull { id -> userCache.read(id) } }
    }

    override suspend fun findValue(valueKey: Int): User? {
        return userCache.read(valueKey)
    }

    override suspend fun updateValue(valueKey: Int, value: User?) {
        userCache.write(valueKey, value)
        idListChannel.send(idListChannel.value)
    }
}
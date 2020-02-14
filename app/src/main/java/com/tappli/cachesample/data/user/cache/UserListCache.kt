package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.data.common.cache.ListChannel
import com.tappli.cachesample.domain.user.model.User
import com.tappli.cachesample.library.cache.ListCache
import com.tappli.cachesample.library.flow.FlowAccessor
import kotlinx.coroutines.flow.Flow

object UserListCache : ListCache<Int, Int, User>, FlowAccessor<Unit, List<User>> {
    private var cachedLastPage: Int = 0

    private val userListChannel = ListChannel<Int, User> { it.id.value }

    val currentPage: Int
        get() = cachedLastPage

    override suspend fun read(key: Int): List<User>? {
        val diff = key - cachedLastPage
        if (diff < 0) {
            clear()
            return null
        }
        val users = userListChannel.getList()
        return if (users.isNotEmpty()) users else null
    }

    override suspend fun write(key: Int, value: List<User>?) {
        val diff = key - cachedLastPage
        if (diff <= 0) {
            clear()
        }
        userListChannel.add(value ?: emptyList())
        cachedLastPage = key
    }

    private suspend fun clear() {
        userListChannel.clear()
        cachedLastPage = 0
    }

    override suspend fun getFlow(key: Unit): Flow<List<User>> {
        return userListChannel.getListFlow()
    }

    override suspend fun findValue(valueKey: Int): User? {
        return userListChannel.valueChannelCache.read(valueKey)
    }

    override suspend fun updateValue(valueKey: Int, value: User?) {
        userListChannel.valueChannelCache.write(valueKey, value)
    }
}
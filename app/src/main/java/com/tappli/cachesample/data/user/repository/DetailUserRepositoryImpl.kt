package com.tappli.cachesample.data.user.repository

import com.tappli.cachesample.data.user.UserApi
import com.tappli.cachesample.data.user.cache.DetailUserCache
import com.tappli.cachesample.data.user.cache.DetailUserUpdater
import com.tappli.cachesample.data.user.cache.UserCaches
import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.repository.DetailUserRepository
import kotlinx.coroutines.flow.Flow

class DetailUserRepositoryImpl : DetailUserRepository {

    override suspend fun getDetailUserFlow(id: UserId): Flow<DetailUser> {
        return DetailUserCache.getFlow(id.value)
    }

    override suspend fun updateDetailUser(id: UserId) {
        DetailUserUpdater.update(id.value)
    }

    override suspend fun setNewCount(id: UserId, newCount: Int) {
        val detailUser = UserApi.setNewCount(id.value, newCount)
        UserCaches.write(id.value, detailUser)
    }

    override suspend fun deleteUser(id: UserId) {
        UserApi.deleteUser(id.value)
        UserCaches.write(id.value, null)
    }
}
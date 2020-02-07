package com.tappli.cachesample.data.user.repository

import com.tappli.cachesample.data.user.UserApi
import com.tappli.cachesample.data.user.cache.DetailUserCache
import com.tappli.cachesample.data.user.cache.DetailUserUpdater
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
        val detailuser = UserApi.setNewCount(id.value, newCount)
        DetailUserCache.write(id.value, detailuser)
    }
}
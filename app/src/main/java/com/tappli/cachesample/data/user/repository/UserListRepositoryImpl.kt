package com.tappli.cachesample.data.user.repository

import com.tappli.cachesample.data.user.cache.UserListCache
import com.tappli.cachesample.data.user.cache.UserListUpdater
import com.tappli.cachesample.domain.user.model.User
import com.tappli.cachesample.domain.user.repository.UserListRepository
import kotlinx.coroutines.flow.Flow

class UserListRepositoryImpl : UserListRepository {
    override suspend fun getUserListFlow(): Flow<List<User>> {
        return UserListCache.getFlow(Unit)
    }

    override suspend fun updateUserList(page: Int) {
        UserListUpdater.update(page)
    }
}
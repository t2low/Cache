package com.tappli.cachesample.domain.user.repository

import com.tappli.cachesample.domain.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserListRepository {
    suspend fun getUserListFlow(): Flow<List<User>>
    suspend fun updateUserList(page: Int)
}

package com.tappli.cachesample.domain.user.repository

import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import kotlinx.coroutines.flow.Flow

interface DetailUserRepository {
    suspend fun getDetailUserFlow(id: UserId): Flow<DetailUser>
    suspend fun updateDetailUser(id: UserId)
    suspend fun setNewCount(id: UserId, newCount: Int)
    suspend fun deleteUser(id: UserId)
}

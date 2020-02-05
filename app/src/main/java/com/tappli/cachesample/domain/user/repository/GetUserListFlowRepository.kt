package com.tappli.cachesample.domain.user.repository

import com.tappli.cachesample.domain.user.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserListFlowRepository {
    suspend fun getUserListFlow(page: Int): Flow<User>
}

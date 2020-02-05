package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserListFlowUseCase {
    suspend fun get(page: Int, forceFetch: Boolean = false): Flow<List<User>>
}

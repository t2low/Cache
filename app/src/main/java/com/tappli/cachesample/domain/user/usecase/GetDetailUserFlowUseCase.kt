package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import kotlinx.coroutines.flow.Flow

interface GetDetailUserFlowUseCase {
    suspend fun get(id: UserId): Flow<DetailUser>
}
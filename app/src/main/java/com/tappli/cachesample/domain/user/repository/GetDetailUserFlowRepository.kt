package com.tappli.cachesample.domain.user.repository

import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import kotlinx.coroutines.flow.Flow

interface GetDetailUserFlowRepository {
    suspend fun getDetailUserFlow(id: UserId): Flow<DetailUser>
}

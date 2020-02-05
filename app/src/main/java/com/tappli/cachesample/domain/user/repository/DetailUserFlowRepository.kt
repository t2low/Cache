package com.tappli.cachesample.domain.user.repository

import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import kotlinx.coroutines.flow.Flow

interface DetailUserFlowRepository {
    suspend fun getDetailUserFlow(id: UserId): Flow<DetailUser>
}

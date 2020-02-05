package com.tappli.cachesample.data.user.repository

import com.tappli.cachesample.data.user.cache.DetailUserCache
import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.repository.GetDetailUserFlowRepository
import kotlinx.coroutines.flow.Flow

class GetDetailUserFlowRepositoryImpl : GetDetailUserFlowRepository {
    override suspend fun getDetailUserFlow(id: UserId): Flow<DetailUser> {
        return DetailUserCache.getFlow(id.value)
    }
}
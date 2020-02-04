package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.repository.DetailUserRepository
import kotlinx.coroutines.flow.Flow

class GetDetailUserFlowUseCaseImpl(
    private val detailUserRepository: DetailUserRepository
) : GetDetailUserFlowUseCase {

    override suspend fun get(id: UserId): Flow<DetailUser> {
        return detailUserRepository.getDetailUserFlow(id)
    }
}
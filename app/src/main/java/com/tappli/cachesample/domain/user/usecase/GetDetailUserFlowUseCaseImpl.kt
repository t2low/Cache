package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.repository.DetailUserFlowRepository
import kotlinx.coroutines.flow.Flow

class GetDetailUserFlowUseCaseImpl(
    private val detailUserFlowRepository: DetailUserFlowRepository
) : GetDetailUserFlowUseCase {

    override suspend fun get(id: UserId, forceFetch: Boolean): Flow<DetailUser> {
        return detailUserFlowRepository.getDetailUserFlow(id)
    }
}
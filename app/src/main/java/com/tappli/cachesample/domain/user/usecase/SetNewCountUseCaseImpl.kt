package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.repository.DetailUserRepository

class SetNewCountUseCaseImpl(private val detailUserRepository: DetailUserRepository) : SetNewCountUseCase {
    override suspend fun set(userId: UserId, newCount: Int) {
        detailUserRepository.setNewCount(userId, newCount)
    }
}
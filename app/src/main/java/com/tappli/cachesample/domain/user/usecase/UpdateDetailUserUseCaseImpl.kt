package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.repository.DetailUserRepository

class UpdateDetailUserUseCaseImpl(private val repository: DetailUserRepository) : UpdateDetailUserUseCase {
    override suspend fun update(id: UserId) {
        repository.updateDetailUser(id)
    }
}
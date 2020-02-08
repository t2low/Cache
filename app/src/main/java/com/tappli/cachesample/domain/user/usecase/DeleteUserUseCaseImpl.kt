package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.repository.DetailUserRepository

class DeleteUserUseCaseImpl(private val userRepository: DetailUserRepository) : DeleteUserUseCase {
    override suspend fun delete(userId: UserId) {
        userRepository.deleteUser(userId)
    }
}
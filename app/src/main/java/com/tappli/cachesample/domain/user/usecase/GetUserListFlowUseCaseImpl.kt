package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.User
import com.tappli.cachesample.domain.user.repository.UserListRepository
import kotlinx.coroutines.flow.Flow

class GetUserListFlowUseCaseImpl(private val userListRepository: UserListRepository) : GetUserListFlowUseCase {
    override suspend fun get(page: Int): Flow<List<User>> {
        return userListRepository.getUserListFlow()
    }
}
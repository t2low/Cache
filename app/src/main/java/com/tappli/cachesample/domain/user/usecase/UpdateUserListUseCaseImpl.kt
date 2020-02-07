package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.repository.UserListRepository

class UpdateUserListUseCaseImpl(private val userListRepository: UserListRepository) : UpdateUserListUseCase {
    override suspend fun update(page: Int) {
        userListRepository.updateUserList(page)
    }
}
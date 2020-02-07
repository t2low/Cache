package com.tappli.cachesample.domain.user.usecase

interface UpdateUserListUseCase {
    suspend fun update(page: Int)
}
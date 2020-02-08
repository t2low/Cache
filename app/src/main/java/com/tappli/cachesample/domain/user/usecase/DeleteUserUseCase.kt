package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.UserId

interface DeleteUserUseCase {
    suspend fun delete(userId: UserId)
}
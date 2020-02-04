package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.UserId

interface UpdateDetailUserUseCase {
    suspend fun update(id: UserId)
}
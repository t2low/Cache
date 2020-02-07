package com.tappli.cachesample.domain.user.usecase

import com.tappli.cachesample.domain.user.model.UserId

interface SetNewCountUseCase {
    suspend fun set(userId: UserId, newCount: Int)
}
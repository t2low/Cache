package com.tappli.cachesample.library.flow

import kotlinx.coroutines.flow.Flow

interface FlowAccessor<KEY : Any, VALUE : Any> {
    suspend fun getFlow(key: KEY): Flow<VALUE>
}
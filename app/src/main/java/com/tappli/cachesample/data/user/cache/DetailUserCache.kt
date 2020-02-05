package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.data.common.cache.ChannelCache
import com.tappli.cachesample.data.common.cache.SizeCache
import com.tappli.cachesample.data.user.UserApi
import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.library.cache.FetchableCache
import com.tappli.cachesample.library.source.Source

object DetailUserCache : ChannelCache<Int, DetailUser>(
    FetchableCache<Int, DetailUser>(
        object : Source<Int, DetailUser> {
            override suspend fun get(key: Int): DetailUser {
                return UserApi.getDetailUser(key)
            }
        },
        SizeCache(10)
    )
)

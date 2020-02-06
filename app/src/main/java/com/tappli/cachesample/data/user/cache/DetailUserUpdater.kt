package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.data.user.UserApi
import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.library.source.Source
import com.tappli.cachesample.library.updater.CacheUpdater

object DetailUserUpdater : CacheUpdater<Int, DetailUser>(
    object : Source<Int, DetailUser> {
        override suspend fun get(key: Int): DetailUser {
            return UserApi.getDetailUser(key)
        }
    },
    UserCaches
)

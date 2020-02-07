package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.data.user.UserApi
import com.tappli.cachesample.domain.user.model.User
import com.tappli.cachesample.library.source.Source
import com.tappli.cachesample.library.updater.CacheUpdater

object UserListUpdater : CacheUpdater<Int, List<User>>(
    object : Source<Int, List<User>> {
        override suspend fun get(key: Int): List<User> {
            return UserApi.getUserList(page = key)
        }
    },
    UserListCache
)

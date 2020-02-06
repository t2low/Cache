package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.library.cache.MergeCache
import com.tappli.cachesample.library.cache.converter.InheritConverter

object UserCaches : MergeCache<Int, DetailUser>(
    DetailUserCache,
    InheritConverter(UserListCache.toValueCache())
)

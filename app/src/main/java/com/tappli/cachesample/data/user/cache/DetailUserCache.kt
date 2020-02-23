package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.library.flow.cache.ValueChannelCache
import com.tappli.cachesample.data.common.cache.SizeCache
import com.tappli.cachesample.domain.user.model.DetailUser

object DetailUserCache : ValueChannelCache<Int, DetailUser>(SizeCache(10))


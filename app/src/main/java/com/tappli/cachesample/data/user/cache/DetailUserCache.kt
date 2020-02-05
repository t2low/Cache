package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.data.common.cache.ChannelCache
import com.tappli.cachesample.data.common.cache.SizeCache
import com.tappli.cachesample.domain.user.model.DetailUser

object DetailUserCache : ChannelCache<Int, DetailUser>(SizeCache(10))


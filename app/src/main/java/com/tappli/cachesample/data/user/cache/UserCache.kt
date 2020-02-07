package com.tappli.cachesample.data.user.cache

import com.tappli.cachesample.data.common.cache.ChannelCache
import com.tappli.cachesample.data.common.cache.SizeCache
import com.tappli.cachesample.domain.user.model.User

object UserCache : ChannelCache<Int, User>(SizeCache(1000))

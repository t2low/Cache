package com.tappli.cachesample.library.flow.cache

import com.tappli.cachesample.library.cache.Cache
import com.tappli.cachesample.library.flow.FlowAccessor

interface FlowAccessorCache<KEY : Any, VALUE : Any> : Cache<KEY, VALUE>, FlowAccessor<KEY, VALUE>
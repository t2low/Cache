package com.tappli.cachesample.library.cache

interface ListCache<LIST_KEY : Any, ITEM_KEY : Any, VALUE : Any> : Cache<LIST_KEY, List<VALUE>> {
    fun toValueCache(): Cache<ITEM_KEY, VALUE> {
        return object : Cache<ITEM_KEY, VALUE> {
            override suspend fun read(key: ITEM_KEY): VALUE? {
                return findValue(key)
            }

            override suspend fun write(key: ITEM_KEY, value: VALUE?) {
                updateValue(key, value)
            }
        }
    }

    suspend fun findValue(valueKey: ITEM_KEY): VALUE?
    suspend fun updateValue(valueKey: ITEM_KEY, value: VALUE?)
}
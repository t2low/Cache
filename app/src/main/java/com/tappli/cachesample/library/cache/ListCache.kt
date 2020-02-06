package com.tappli.cachesample.library.cache

interface ListCache<LIST_KEY : Any, VALUE_KEY : Any, VALUE : Any> : Cache<LIST_KEY, List<VALUE>> {
    fun toValueCache(): Cache<VALUE_KEY, VALUE> {
        return object : Cache<VALUE_KEY, VALUE> {
            override suspend fun read(key: VALUE_KEY): VALUE? {
                return findValue(key)
            }

            override suspend fun write(key: VALUE_KEY, value: VALUE?) {
                updateValue(key, value)
            }
        }
    }

    suspend fun findValue(valueKey: VALUE_KEY): VALUE?
    suspend fun updateValue(valueKey: VALUE_KEY, value: VALUE?)
}
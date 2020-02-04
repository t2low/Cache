package com.tappli.cachesample.library.cache

open class WritableConverter<KEY : Any, IN : Any, OUT : Any>(
    private val writable: Writable<KEY, OUT>,
    private val converter: (IN?) -> OUT?
) : Writable<KEY, IN> {

    override suspend fun write(key: KEY, value: IN?) {
        writable.write(key, converter(value))
    }
}

open class InheritConverter<KEY : Any, IN : OUT, OUT : Any>(
    writable: Writable<KEY, OUT>
) : WritableConverter<KEY, IN, OUT>(writable, ::convertInheritObject)

fun <IN : OUT, OUT : Any> convertInheritObject(value: IN?): OUT? = value


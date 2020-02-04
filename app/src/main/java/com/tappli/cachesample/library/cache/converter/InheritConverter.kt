package com.tappli.cachesample.library.cache.converter

import com.tappli.cachesample.library.cache.Writable

open class InheritConverter<KEY : Any, IN : OUT, OUT : Any>(
    writable: Writable<KEY, OUT>
) : WritableConverter<KEY, IN, OUT>(writable, ::convertInheritObject)

fun <IN : OUT, OUT : Any> convertInheritObject(value: IN?): OUT? = value

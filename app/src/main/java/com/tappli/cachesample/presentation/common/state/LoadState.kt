package com.tappli.myapplication.presentation.common

sealed class LoadState<out T> {
    object Loading : LoadState<Nothing>()
    class Loaded<T>(val value: T) : LoadState<T>()
    class Error<T>(val e: Throwable) : LoadState<T>()
}
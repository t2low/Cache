package com.tappli.myapplication.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LoadStateLiveDataDelegate<T>(loadState: LiveData<LoadState<T>>, value: LiveData<T>) :
    ReadOnlyProperty<ViewModel, LiveData<LoadState<T>>> {
    private val liveData =
        MediatorLiveData<LoadState<T>>().apply {
            addSource(loadState) {
                this.value = it
            }
            addSource(value) {
                this.value = LoadState.Loaded(it)
            }
        }

    override fun getValue(thisRef: ViewModel, property: KProperty<*>): LiveData<LoadState<T>> {
        return liveData
    }

}
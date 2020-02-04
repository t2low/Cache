package com.tappli.cachesample.presentation

import android.app.Application
import androidx.lifecycle.*
import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.usecase.GetDetailUserFlowUseCase
import com.tappli.cachesample.domain.user.usecase.UpdateDetailUserUseCase
import com.tappli.myapplication.presentation.common.LoadState
import com.tappli.myapplication.presentation.common.LoadStateLiveDataDelegate
import kotlinx.coroutines.launch

class DetailUserViewModel(
    application: Application,
    private val userId: UserId,
    private val getDetailUserFlowUseCase: GetDetailUserFlowUseCase,
    private val updateDetailUserUseCase: UpdateDetailUserUseCase
) : AndroidViewModel(application) {

    private val loadState = MutableLiveData<LoadState<DetailUser>>(LoadState.Loading)
    private val detailUser: LiveData<DetailUser> = liveData {
        emitSource(getDetailUserFlowUseCase.get(userId).asLiveData())
    }
    val detailUserState by LoadStateLiveDataDelegate(loadState, detailUser)

    init {
        requestUpdateDetailUser(userId)
    }

    private fun requestUpdateDetailUser(userId: UserId) = viewModelScope.launch {
        try {
            loadState.value = LoadState.Loading
            updateDetailUserUseCase.update(userId)
        } catch (e: Exception) {
            loadState.value = LoadState.Error(e)
        }
    }
}
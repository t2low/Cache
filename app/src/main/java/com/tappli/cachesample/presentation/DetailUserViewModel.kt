package com.tappli.cachesample.presentation

import android.app.Application
import androidx.lifecycle.*
import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.usecase.GetDetailUserFlowUseCase
import com.tappli.cachesample.domain.user.usecase.UpdateDetailUserUseCase
import kotlinx.coroutines.launch

class DetailUserViewModel(
    application: Application,
    private val userId: UserId,
    private val getDetailUserFlowUseCase: GetDetailUserFlowUseCase,
    private val updateDetailUserUseCase: UpdateDetailUserUseCase
) : AndroidViewModel(application) {

    val detailUser: LiveData<DetailUser> = liveData {
        emitSource(getDetailUserFlowUseCase.get(userId).asLiveData())
    }

    init {
        requestUpdateDetailUser(userId)
    }

    private fun requestUpdateDetailUser(userId: UserId) = viewModelScope.launch {
        try {
            updateDetailUserUseCase.update(userId)
        } catch (e: Exception) {
        }
    }
}
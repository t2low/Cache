package com.tappli.cachesample.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.usecase.GetDetailUserFlowUseCase

class DetailUserViewModel(
    application: Application,
    private val userId: UserId,
    private val getDetailUserFlowUseCase: GetDetailUserFlowUseCase
) : AndroidViewModel(application) {

    val detailUser: LiveData<DetailUser> = liveData {
        emitSource(getDetailUserFlowUseCase.get(userId).asLiveData())
    }
}
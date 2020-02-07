package com.tappli.cachesample.presentation

import android.app.Application
import androidx.lifecycle.*
import com.tappli.cachesample.domain.user.model.User
import com.tappli.cachesample.domain.user.usecase.GetUserListFlowUseCase
import com.tappli.cachesample.domain.user.usecase.UpdateUserListUseCase
import com.tappli.myapplication.presentation.common.LoadState
import com.tappli.myapplication.presentation.common.LoadStateLiveDataDelegate
import kotlinx.coroutines.launch

class UserListViewModel(
    application: Application,
    private val getUserListFlowUseCase: GetUserListFlowUseCase,
    private val updateUserListUseCase: UpdateUserListUseCase
) : AndroidViewModel(application) {

    private val loadState = MutableLiveData<LoadState<List<User>>>(LoadState.Loading)
    private val userList: LiveData<List<User>> = liveData {
        emitSource(getUserListFlowUseCase.get().asLiveData())
    }
    val detailUserState by LoadStateLiveDataDelegate(loadState, userList)

    init {
        requestUpdateUserList(1)
    }

    private fun requestUpdateUserList(page: Int) = viewModelScope.launch {
        try {
            loadState.value = LoadState.Loading
            updateUserListUseCase.update(page)
        } catch (e: Exception) {
            loadState.value = LoadState.Error(e)
        }
    }
}
package com.tappli.cachesample

import android.app.Application
import com.tappli.cachesample.data.user.repository.DetailUserRepositoryImpl
import com.tappli.cachesample.data.user.repository.UserListRepositoryImpl
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.cachesample.domain.user.repository.DetailUserRepository
import com.tappli.cachesample.domain.user.repository.UserListRepository
import com.tappli.cachesample.domain.user.usecase.*
import com.tappli.cachesample.presentation.DetailUserViewModel
import com.tappli.cachesample.presentation.UserListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CacheApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CacheApplication)
            modules(
                listOf(
                    module {
                        single<DetailUserRepository> { DetailUserRepositoryImpl() }
                        single<UserListRepository> { UserListRepositoryImpl() }
                    },
                    module {
                        single<GetDetailUserFlowUseCase> { GetDetailUserFlowUseCaseImpl(get()) }
                        single<UpdateDetailUserUseCase> { UpdateDetailUserUseCaseImpl(get()) }
                        single<SetNewCountUseCase> { SetNewCountUseCaseImpl(get()) }
                        single<DeleteUserUseCase> { DeleteUserUseCaseImpl(get()) }
                        single<GetUserListFlowUseCase> { GetUserListFlowUseCaseImpl(get()) }
                        single<UpdateUserListUseCase> { UpdateUserListUseCaseImpl(get()) }
                    },
                    module {
                        viewModel { (userId: UserId) -> DetailUserViewModel(get(), userId, get(), get(), get(), get()) }
                        viewModel { UserListViewModel(get(), get(), get()) }
                    }
                )
            )
        }
    }
}
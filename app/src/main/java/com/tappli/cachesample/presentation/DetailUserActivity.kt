package com.tappli.cachesample.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.tappli.cachesample.R
import com.tappli.cachesample.domain.user.model.UserId
import com.tappli.myapplication.presentation.common.LoadState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailUserActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        fun createIntent(context: Context, userId: UserId): Intent {
            return Intent(context, DetailUserActivity::class.java).apply {
                putExtra(Params.UserId.name, userId.value)
            }
        }
    }

    private enum class Params {
        UserId
    }

    private val userId: UserId by lazy { UserId(intent.getIntExtra(Params.UserId.name, 0)) }
    private val detailUserViewModel by viewModel<DetailUserViewModel> { parametersOf(userId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailUserViewModel.detailUserState.observe(this, Observer { state ->
            when (state) {
                is LoadState.Loading -> {
                    helloTextView.text = "Loading..."
                }
                is LoadState.Loaded -> {
                    helloTextView.text = state.value.run { "${id.value}: ${name} (${count}) - ${message}" }
                }
                is LoadState.Error -> {
                    helloTextView.text = state.e.localizedMessage
                }
            }

        })
    }
}

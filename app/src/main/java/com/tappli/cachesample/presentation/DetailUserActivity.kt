package com.tappli.cachesample.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

        countUpButton.setOnClickListener { detailUserViewModel.countUp() }
        deleteButton.setOnClickListener { detailUserViewModel.deleteUser() }

        detailUserViewModel.detailUserState.observe(this, Observer { state ->
            when (state) {
                is LoadState.Loading -> {
                    touchGuardTextView.text = "Loading..."
                    touchGuardTextView.isVisible = true
                }
                is LoadState.Loaded -> {
                    touchGuardTextView.isVisible = false
                    state.value.run {
                        idTextView.text = id.value.toString()
                        nameTextView.text = name
                        messageTextView.text = message
                        countTextView.text = count.toString()
                    }
                }
                is LoadState.Error -> {
                    touchGuardTextView.text = state.e.toString()
                    touchGuardTextView.isVisible = true
                }
            }
        })
        detailUserViewModel.closeView.observe(this, Observer {
            finish()
        })
    }
}

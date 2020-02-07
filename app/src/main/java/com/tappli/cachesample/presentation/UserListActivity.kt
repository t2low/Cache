package com.tappli.cachesample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.tappli.cachesample.R
import com.tappli.cachesample.domain.user.model.User
import com.tappli.myapplication.presentation.common.LoadState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.android.synthetic.main.item_user.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListActivity : AppCompatActivity(R.layout.activity_user_list) {

    private data class UserItem(private val user: User) : Item(user.id.value.toLong()) {
        override fun getLayout(): Int = R.layout.item_user

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.apply {
                idTextView.text = user.id.value.toString()
                nameTextView.text = user.name
                countTextView.text = user.count.toString()
            }
        }
    }

    private val userListViewModel by viewModel<UserListViewModel>()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userListViewModel.detailUserState.observe(this, Observer {
            when (it) {
                is LoadState.Loading -> {
                    messageTextView.text = "Loading..."
                }
                is LoadState.Loaded -> {
                    messageTextView.text = ""
                    adapter.updateAsync(it.value.map { user -> UserItem(user) })
                }
                is LoadState.Error -> {
                    messageTextView.text = it.e.toString()
                }
            }
        })
        userListView.adapter = adapter
    }
}

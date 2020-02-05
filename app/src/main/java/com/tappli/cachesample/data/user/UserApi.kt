package com.tappli.cachesample.data.user

import com.tappli.cachesample.domain.user.model.DetailUser
import com.tappli.cachesample.domain.user.model.User
import com.tappli.cachesample.domain.user.model.UserId
import kotlinx.coroutines.delay
import kotlin.math.max

object UserApi {

    suspend fun getDetailUser(id: Int): DetailUser {
        delay(1000)
        return makeUser(id)
    }

    suspend fun getUserList(page: Int): List<User> {
        delay(1500)

        val lengthPerPage = 20
        val startId = max(page, 1) * lengthPerPage
        val endId = startId + lengthPerPage - 1

        return (startId..endId).map { makeUser(it) }
    }

    private fun makeUser(id: Int): DetailUser {
        return DetailUser(
            UserId(id), "No.${id}", 0, "message ${id}"
        )
    }
}
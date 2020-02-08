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

    suspend fun setNewCount(id: Int, count: Int): DetailUser {
        delay(500)
        return makeUser(id, count)
    }

    suspend fun getUserList(page: Int): List<User> {
        delay(1500)

        val lengthPerPage = 20
        val startId = max(page - 1, 0) * lengthPerPage + 1
        val endId = startId + lengthPerPage - 1

        return (startId..endId).map { makeUser(it) }
    }

    suspend fun deleteUser(userId: Int) {
        delay(500)
    }

    private fun makeUser(id: Int, count: Int = 0): DetailUser {
        return DetailUser(
            UserId(id), "Name#${id}", count, "message ${id}"
        )
    }
}
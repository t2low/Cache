package com.tappli.cachesample.domain.user.model

class DetailUser(
    id: UserId,
    name: String,
    count: Int,
    val message: String
) : User(id, name, count)
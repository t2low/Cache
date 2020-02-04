package com.tappli.cachesample.domain.model.user

class DetailUser(
    id: UserId,
    name: String,
    count: Int,
    val message: String
) : User(id, name, count)
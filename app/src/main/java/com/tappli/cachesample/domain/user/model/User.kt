package com.tappli.cachesample.domain.user.model

open class User(
    val id: UserId,
    val name: String,
    var count: Int
)
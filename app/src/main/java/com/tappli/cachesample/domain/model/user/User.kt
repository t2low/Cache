package com.tappli.cachesample.domain.model.user

open class User(
    val id: UserId,
    val name: String,
    var count: Int
)
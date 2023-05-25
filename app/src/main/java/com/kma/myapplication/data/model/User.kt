package com.kma.myapplication.data.model

data class User(
    val accessToken: String,
    val refreshToken: String,
    val success: Boolean,
    val user: UserX
)
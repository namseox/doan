package com.kma.myapplication.data.api.retrofit.user


import com.kma.myapplication.data.model.Login
import com.kma.myapplication.data.model.User
import retrofit2.http.*

interface LoginApi {
    @POST("auth/login")
    suspend fun postUserLogin(
        @Body login:Login
    ): User

}
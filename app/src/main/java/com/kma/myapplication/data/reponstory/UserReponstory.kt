package com.kma.myapplication.data.reponstory

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.Login
import com.kma.myapplication.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserReponstory(val context: Context) {
    private var loginApiHelper = ApiHelper.getInstance(context)
    suspend fun userLogin( userName: String, password: String):User? = withContext(Dispatchers.Default) {
        try {
            loginApiHelper.loginApi.postUserLogin( Login(userName, password))

        }catch (ex: Exception){
            Log.d("TAG", "userLogin: "+ex)
            null
        }
    }
}
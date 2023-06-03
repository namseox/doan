package com.kma.myapplication.data.api.retrofit.apihelper

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kma.myapplication.data.api.base.BaseRetrofitHelper
import com.kma.myapplication.data.api.retrofit.book.BookApi
import com.kma.myapplication.data.api.retrofit.staff.StaffApi
import com.kma.myapplication.data.api.retrofit.user.LoginApi
import com.kma.myapplication.utils.SingletonHolder
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ApiHelper private constructor(context: Context) : BaseRetrofitHelper(context) {
        private val BASE_URL = "http://192.168.1.22:8001/"
//    private val BASE_URL = "http://127.0.0.1:5173"
    var loginApi: LoginApi
    var staffApi: StaffApi
    var bookApi:BookApi

    init {
        GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(
                CoroutineCallAdapterFactory()
            ).client(okHttpClient!!).build()
        loginApi = retrofit.create(LoginApi::class.java)
        staffApi = retrofit.create(StaffApi::class.java)
        bookApi = retrofit.create(BookApi::class.java)

    }

    companion object : SingletonHolder<ApiHelper, Context>(::ApiHelper)

}
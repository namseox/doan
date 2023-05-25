package com.kma.myapplication.data.api.retrofit.apihelper

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kma.myapplication.data.api.base.BaseRetrofitHelper
import com.kma.myapplication.data.api.retrofit.staff.StaffApi
import com.kma.myapplication.data.api.retrofit.user.LoginApi
import com.kma.myapplication.utils.SingletonHolder
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ApiHelper private constructor(context: Context) : BaseRetrofitHelper(context) {
    //    private val BASE_URL = "http://192.130.15.106//:8001/"
    private val BASE_URL = "http://192.130.15.105:8001"
    var loginApi: LoginApi
    var staffApi: StaffApi

    init {
        GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(
                CoroutineCallAdapterFactory()
            ).client(okHttpClient!!).build()
        loginApi = retrofit.create(LoginApi::class.java)
        staffApi = retrofit.create(StaffApi::class.java)
    }

    companion object : SingletonHolder<ApiHelper, Context>(::ApiHelper)

}
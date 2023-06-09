package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.Year
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class YearRepository (val context: Context) {
    private var loginApiHelper = ApiHelper.getInstance(context)
    suspend fun getListYear(): List<Year>? = withContext(Dispatchers.Default) {
        try {
            loginApiHelper.apiYear.getListYear()

        }catch (ex: Exception){
            Log.d("TAG", "userLogin: "+ex)
            null
        }
    }
}
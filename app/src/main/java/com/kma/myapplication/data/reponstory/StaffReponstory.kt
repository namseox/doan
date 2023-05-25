package com.kma.myapplication.data.reponstory

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.data.model.StaffItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.util.Objects

class StaffReponstory(val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListStaff(): List<StaffItem>? = withContext(Dispatchers.Default){
        try {
            apiHelper.staffApi.getStaff()
        }catch (e: Exception){
            Log.d("TAG", "getListStaff: "+e)
            null
        }
    }
}
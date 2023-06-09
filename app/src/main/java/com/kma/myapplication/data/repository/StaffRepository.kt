package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.CreateUser
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StaffRepository(val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListStaff(): List<StaffItem>? = withContext(Dispatchers.Default){
        try {
            apiHelper.staffApi.getListStaff()
        }catch (e: Exception){
            Log.d("TAG", "getListStaff: "+e)
            null
        }
    }
    suspend fun getStaff(id:Int,year_id:Int): UserX? = withContext(Dispatchers.Default){
        try {
            apiHelper.staffApi.getStaff(id,year_id)
        }catch (e: Exception){
            Log.d("TAG", "getListStaff----: "+e)
            null
        }
    }

    suspend fun deleteStaff(id: Int): Int? = withContext(Dispatchers.Default){
        try {
            apiHelper.staffApi.deleteStaff(id)
        }catch (e: Exception){
            Log.d("TAG", "getListStaff222: "+e)
            null
        }
    }

    suspend fun createUser(user: UserX): CreateUser? = withContext(Dispatchers.Default){
        try {
            apiHelper.staffApi.creatStaff(user)
        }catch (e: Exception){
            Log.d("TAG", "getListStaff222: "+e)
            null
        }
    }

    suspend fun updateUser(id:Int,user: UserX): StaffItem? = withContext(Dispatchers.Default){
        try {
            apiHelper.staffApi.updateStaff(id,user)
        }catch (e: Exception){
            Log.d("TAG", "getListStaff222: "+e)
            null
        }
    }
}
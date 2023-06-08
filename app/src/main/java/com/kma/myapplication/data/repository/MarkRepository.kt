package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.MarkItem
import com.kma.myapplication.data.model.ListMarkItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarkRepository (val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListMark(): List<ListMarkItem>? = withContext(Dispatchers.Default) {
        try {
            apiHelper.markApi.getListMark()

        }catch (ex: Exception){
            Log.d("TAG", "getListBook: "+ex)
            null
        }
    }

    suspend fun creatMark(item: ListMarkItem): ListMarkItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.markApi.creatMark(item)

        }catch (ex: Exception){
            Log.d("TAG", "creatBook: "+ex)
            null
        }
    }
    suspend fun getItemMark(id:Int ): MarkItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.markApi.getItemMark(id)

        }catch (ex: Exception){
            Log.d("TAG", "getItemBook00: "+ex)
            null
        }
    }
    suspend fun delete(id:Int ): Int? = withContext(Dispatchers.Default) {
        try {
            apiHelper.markApi.deleteMark(id)

        }catch (ex: Exception){
            Log.d("TAG", "delete: "+ex)
            null
        }
    }
    suspend fun updateMark(id:Int,item: ListMarkItem): ListMarkItem? = withContext(Dispatchers.Default) {
        try {

            apiHelper.markApi.updateMark(id,item)

        }catch (ex: Exception){
            Log.d("TAG", "updateBook: "+ex)
            null
        }
    }

}
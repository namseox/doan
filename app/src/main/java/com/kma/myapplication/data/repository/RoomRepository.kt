package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.ListRoomItem
import com.kma.myapplication.data.model.RoomItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository(val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListRoom(): List<ListRoomItem>? = withContext(Dispatchers.Default) {
        try {
            apiHelper.roomApi.getListRoom()

        }catch (ex: Exception){
            Log.d("TAG", "getListBook: "+ex)
            null
        }
    }

    suspend fun creatRoom(item: ListRoomItem): ListRoomItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.roomApi.creatRoom(item)

        }catch (ex: Exception){
            Log.d("TAG", "creatBook: "+ex)
            null
        }
    }
    suspend fun getItemRoom(id:Int ): RoomItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.roomApi.getItemRoom(id)

        }catch (ex: Exception){
            Log.d("TAG", "getItemBook00: "+ex)
            null
        }
    }
    suspend fun delete(id:Int ): Int? = withContext(Dispatchers.Default) {
        try {
            apiHelper.roomApi.deleteRoom(id)

        }catch (ex: Exception){
            Log.d("TAG", "delete: "+ex)
            null
        }
    }
    suspend fun updateRoom(id:Int,item: ListRoomItem): ListRoomItem? = withContext(Dispatchers.Default) {
        try {

            apiHelper.roomApi.updateRoom(id,item)

        }catch (ex: Exception){
            Log.d("TAG", "updateBook: "+ex)
            null
        }
    }

}
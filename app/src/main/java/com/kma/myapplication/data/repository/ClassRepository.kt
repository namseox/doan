package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.ClassItem
import com.kma.myapplication.data.model.DashboardClass
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.model.ListClassItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClassRepository (val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListClass(): List<ListClassItem>? = withContext(Dispatchers.Default) {
        try {
            apiHelper.classApi.getListClass()

        }catch (ex: Exception){
            Log.d("TAG", "getListBook: "+ex)
            null
        }
    }
    suspend fun getListDashboardClass(year_id :Int,user_id :Int): List<DashboardClass>? = withContext(Dispatchers.Default) {
        try {
            apiHelper.classApi.getListDashboardClass(year_id,user_id)

        }catch (ex: Exception){
            Log.d("TAG", "getListBook: "+ex)
            null
        }
    }

    suspend fun creatClass(item: ListClassItem): ListClassItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.classApi.creatClass(item)

        }catch (ex: Exception){
            Log.d("TAG", "creatBook: "+ex)
            null
        }
    }
    suspend fun getItemClass(id:Int ): ClassItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.classApi.getItemClass(id)

        }catch (ex: Exception){
            Log.d("TAG", "getItemBook00: "+ex)
            null
        }
    }
    suspend fun delete(id:Int ): Int? = withContext(Dispatchers.Default) {
        try {
            apiHelper.classApi.deleteClass(id)

        }catch (ex: Exception){
            Log.d("TAG", "delete: "+ex)
            null
        }
    }
    suspend fun updateClass(id:Int,item: ListClassItem): ListClassItem? = withContext(Dispatchers.Default) {
        try {

            apiHelper.classApi.updateClass(id,item)

        }catch (ex: Exception){
            Log.d("TAG", "updateBook: "+ex)
            null
        }
    }
}
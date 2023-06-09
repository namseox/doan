package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.SubjectItem
import com.kma.myapplication.data.model.ListSubjectItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubjectRepository (val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListSubject(): List<ListSubjectItem>? = withContext(Dispatchers.Default) {
        try {
            apiHelper.subjectApi.getListSubject()

        }catch (ex: Exception){
            Log.d("TAG", "getListBook: "+ex)
            null
        }
    }

    suspend fun creatSubject(item: ListSubjectItem): ListSubjectItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.subjectApi.creatSubject(item)

        }catch (ex: Exception){
            Log.d("TAG", "creatBook: "+ex)
            null
        }
    }
    suspend fun getItemSubject(id:Int ): SubjectItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.subjectApi.getItemSubject(id)

        }catch (ex: Exception){
            Log.d("TAG", "getItemBook00: "+ex)
            null
        }
    }
    suspend fun delete(id:Int ): Int? = withContext(Dispatchers.Default) {
        try {
            apiHelper.subjectApi.deleteSubject(id)

        }catch (ex: Exception){
            Log.d("TAG", "delete: "+ex)
            null
        }
    }
    suspend fun updateSubject(id:Int,item: ListSubjectItem): ListSubjectItem? = withContext(Dispatchers.Default) {
        try {

            apiHelper.subjectApi.updateSubject(id,item)

        }catch (ex: Exception){
            Log.d("TAG", "updateBook: "+ex)
            null
        }
    }

}
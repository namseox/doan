package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.ExamItem
import com.kma.myapplication.data.model.ListExamItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExamRepository (val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListExam(): List<ListExamItem>? = withContext(Dispatchers.Default) {
        try {
            apiHelper.examApi.getListExam()

        }catch (ex: Exception){
            Log.d("TAG", "getListBook: "+ex)
            null
        }
    }

    suspend fun creatExam(item: ListExamItem): ListExamItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.examApi.creatExam(item)

        }catch (ex: Exception){
            Log.d("TAG", "creatBook: "+ex)
            null
        }
    }
    suspend fun getItemExam(id:Int ): ExamItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.examApi.getItemExam(id)

        }catch (ex: Exception){
            Log.d("TAG", "getItemBook00: "+ex)
            null
        }
    }
    suspend fun delete(id:Int ): Int? = withContext(Dispatchers.Default) {
        try {
            apiHelper.examApi.deleteExam(id)

        }catch (ex: Exception){
            Log.d("TAG", "delete: "+ex)
            null
        }
    }
    suspend fun updateExam(id:Int,item: ListExamItem): ListExamItem? = withContext(Dispatchers.Default) {
        try {

            apiHelper.examApi.updateExam(id,item)

        }catch (ex: Exception){
            Log.d("TAG", "updateBook: "+ex)
            null
        }
    }

}
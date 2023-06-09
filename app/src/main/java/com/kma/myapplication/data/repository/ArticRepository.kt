package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.ArticleCreat
import com.kma.myapplication.data.model.ArticleItem
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.model.ListBookItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticRepository(val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListArtic(): List<ListArticleItem>? = withContext(Dispatchers.Default) {
        try {
            apiHelper.articApi.getListArticle()

        }catch (ex: Exception){
            Log.d("TAG", "getListBook: "+ex)
            null
        }
    }

    suspend fun creatArtic(item: ArticleCreat): ListArticleItem? = withContext(Dispatchers.Default) {
        try {
            Log.d("TAG", "creatArtic000: "+item)
            apiHelper.articApi.creatArticle(item)

        }catch (ex: Exception){
            Log.d("TAG", "creatBook: "+ex)
            null
        }
    }
    suspend fun getItemArtic(id:Int ): ArticleItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.articApi.getItemArticle(id)

        }catch (ex: Exception){
            Log.d("TAG", "getItemBook00: "+ex)
            null
        }
    }
    suspend fun delete(id:Int ): Int? = withContext(Dispatchers.Default) {
        try {
            apiHelper.articApi.deleteArticle(id)

        }catch (ex: Exception){
            Log.d("TAG", "delete: "+ex)
            null
        }
    }
    suspend fun updateBook(id:Int,item: ArticleCreat): ListArticleItem? = withContext(Dispatchers.Default) {
        try {
            Log.d("TAG", "creatArtic000: "+item)
            apiHelper.articApi.updateArticle(id,item)

        }catch (ex: Exception){
            Log.d("TAG", "updateBook: "+ex)
            null
        }
    }
}
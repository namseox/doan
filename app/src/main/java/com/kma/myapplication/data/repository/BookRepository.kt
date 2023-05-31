package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListBookItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(val context: Context) {
    private var apiHelper = ApiHelper.getInstance(context)
    suspend fun getListBook(): List<ListBookItem>? = withContext(Dispatchers.Default) {
        try {
            apiHelper.bookApi.getListBook()

        }catch (ex: Exception){
            Log.d("TAG", "getListBook: "+ex)
            null
        }
    }

    suspend fun creatBook(itemBook:ListBookItem ): ListBookItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.bookApi.creatBook(itemBook)

        }catch (ex: Exception){
            Log.d("TAG", "creatBook: "+ex)
            null
        }
    }
    suspend fun getItemBook(id:Int ): Book? = withContext(Dispatchers.Default) {
        try {
            apiHelper.bookApi.getItemBook(id)

        }catch (ex: Exception){
            Log.d("TAG", "getItemBook: "+ex)
            null
        }
    }
    suspend fun delete(id:Int ): Int? = withContext(Dispatchers.Default) {
        try {
            apiHelper.bookApi.deleteBook(id)

        }catch (ex: Exception){
            Log.d("TAG", "delete: "+ex)
            null
        }
    }
    suspend fun updateBook(id:Int,itemBook: ListBookItem ): ListBookItem? = withContext(Dispatchers.Default) {
        try {
            apiHelper.bookApi.updateBook(id,itemBook)

        }catch (ex: Exception){
            Log.d("TAG", "updateBook: "+ex)
            null
        }
    }
}
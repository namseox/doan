package com.kma.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.kma.myapplication.data.api.retrofit.apihelper.ApiHelper
import com.kma.myapplication.data.model.TopicItemMainItem
import com.kma.myapplication.data.model.Year
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopicRepository (val context: Context) {
    private var loginApiHelper = ApiHelper.getInstance(context)
    suspend fun getListTopicItemMainItem(id :Int): List<TopicItemMainItem>? = withContext(Dispatchers.Default) {
        try {
            loginApiHelper.topicApi.getListTopicItemMainItem(id)

        }catch (ex: Exception){
            Log.d("TAG", "userLogin: "+ex)
            null
        }
    }
}
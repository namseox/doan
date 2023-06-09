package com.kma.myapplication.data.api.retrofit.topic

import com.kma.myapplication.data.model.TopicItemMainItem
import com.kma.myapplication.data.model.Year
import retrofit2.http.GET
import retrofit2.http.Query

interface TopicApi {
    @GET("/topic?sort=desc&sortColumn=id")
    suspend fun getListTopicItemMainItem(
        @Query("user_id") user_id : Int
    ): List<TopicItemMainItem>?
}
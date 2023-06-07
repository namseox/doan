package com.kma.myapplication.data.api.retrofit.mark

import com.kma.myapplication.data.model.ListMarkItem
import com.kma.myapplication.data.model.MarkItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MarkApi {
    @GET("/mark")
    suspend fun getListListMark(
    ): List<ListMarkItem>

    @GET("/mark/{id}")
    suspend fun getItemMark(@Path("id") i: Int): MarkItem

    @DELETE("/mark/{id}")
    suspend fun deleteMark(@Path("id") i: Int): Int

    @POST("/mark")
    suspend fun creatMark(
        @Body itemMark: ListMarkItem
    ): ListMarkItem

    @PUT("/mark/{id}")
    suspend fun updateMark(
        @Path("id") i: Int,
        @Body itemMark: ListMarkItem
    ): ListMarkItem
}
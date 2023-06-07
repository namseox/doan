package com.kma.myapplication.data.api.retrofit.invention

import com.kma.myapplication.data.model.InventionItem
import com.kma.myapplication.data.model.ListInventionItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Invention {
    @GET("/invention")
    suspend fun getListListInvention(
    ): List<ListInventionItem>

    @GET("/invention/{id}")
    suspend fun getItemInvention(@Path("id") i: Int): ListInventionItem

    @DELETE("/invention/{id}")
    suspend fun deleteInvention(@Path("id") i: Int): Int

    @POST("/invention")
    suspend fun creatInvention(
        @Body itemInvention: ListInventionItem
    ): ListInventionItem

    @PUT("/invention/{id}")
    suspend fun updateInvention(
        @Path("id") i: Int,
        @Body itemInvention: ListInventionItem
    ): ListInventionItem
}
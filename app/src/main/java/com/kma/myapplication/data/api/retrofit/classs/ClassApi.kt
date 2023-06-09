package com.kma.myapplication.data.api.retrofit.classs

import com.kma.myapplication.data.model.ClassItem
import com.kma.myapplication.data.model.DashboardClass
import com.kma.myapplication.data.model.ListClassItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ClassApi {
    @GET("/class")
    suspend fun getListClass(
    ): List<ListClassItem>
    @GET("/class/dashboard")
    suspend fun getListDashboardClass(
        @Query("year_id") year_id :Int,
        @Query("user_id") user_id :Int
    ): List<DashboardClass>
    @GET("/class/{id}")
    suspend fun getItemClass(@Path("id") i: Int): ClassItem

    @DELETE("/class/{id}")
    suspend fun deleteClass(@Path("id") i: Int): Int

    @POST("/class")
    suspend fun creatClass(
        @Body itemClass: ListClassItem
    ): ListClassItem

    @PUT("/class/{id}")
    suspend fun updateClass(
        @Path("id") i: Int,
        @Body itemBook: ListClassItem
    ): ListClassItem
}
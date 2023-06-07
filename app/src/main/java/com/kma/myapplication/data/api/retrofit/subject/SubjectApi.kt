package com.kma.myapplication.data.api.retrofit.subject

import com.kma.myapplication.data.model.ListSubjectItem
import com.kma.myapplication.data.model.SubjectItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SubjectApi {
    @GET("/room")
    suspend fun getListListSubject(
    ): List<ListSubjectItem>

    @GET("/room/{id}")
    suspend fun getItemSubject(@Path("id") i: Int): SubjectItem

    @DELETE("/room/{id}")
    suspend fun deleteSubject(@Path("id") i: Int): Int

    @POST("/room")
    suspend fun creatSubject(
        @Body itemSubject: ListSubjectItem
    ): ListSubjectItem

    @PUT("/room/{id}")
    suspend fun updateSubject(
        @Path("id") i: Int,
        @Body itemSubject: ListSubjectItem
    ): ListSubjectItem
}
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
    @GET("/subject")
    suspend fun getListSubject(
    ): List<ListSubjectItem>

    @GET("/subject/{id}")
    suspend fun getItemSubject(@Path("id") i: Int): SubjectItem

    @DELETE("/subject/{id}")
    suspend fun deleteSubject(@Path("id") i: Int): Int

    @POST("/subject")
    suspend fun creatSubject(
        @Body itemSubject: ListSubjectItem
    ): ListSubjectItem

    @PUT("/subject/{id}")
    suspend fun updateSubject(
        @Path("id") i: Int,
        @Body itemSubject: ListSubjectItem
    ): ListSubjectItem
}
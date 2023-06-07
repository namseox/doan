package com.kma.myapplication.data.api.retrofit.exam



import com.kma.myapplication.data.model.ExamItem
import com.kma.myapplication.data.model.ListExamItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ExamApi {
    @GET("/exam")
    suspend fun getListExam(
    ): List<ListExamItem>

    @GET("/exam/{id}")
    suspend fun getItemExam(@Path("id") i: Int): ExamItem

    @DELETE("/exam/{id}")
    suspend fun deleteExam(@Path("id") i: Int): Int

    @POST("/exam")
    suspend fun creatExam(
        @Body itemClass: ListExamItem
    ): ListExamItem

    @PUT("/exam/{id}")
    suspend fun updateExam(
        @Path("id") i: Int,
        @Body itemExam: ListExamItem
    ): ListExamItem
}
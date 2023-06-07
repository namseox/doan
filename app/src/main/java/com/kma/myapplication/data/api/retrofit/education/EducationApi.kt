package com.kma.myapplication.data.api.retrofit.education

import com.kma.myapplication.data.model.Class
import com.kma.myapplication.data.model.EducationItem
import com.kma.myapplication.data.model.ListClassItem
import com.kma.myapplication.data.model.ListEducationItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EducationApi {
    @GET("/education")
    suspend fun getListEducation(
    ): List<ListEducationItem>

    @GET("/education/{id}")
    suspend fun getItemEducation(@Path("id") i: Int): EducationItem

    @DELETE("/education/{id}")
    suspend fun deleteEducation(@Path("id") i: Int): Int

    @POST("/education")
    suspend fun creatEducation(
        @Body itemEducation: ListEducationItem
    ): ListEducationItem

    @PUT("/education/{id}")
    suspend fun updateEducation(
        @Path("id") i: Int,
        @Body itemEducation: ListEducationItem
    ): ListEducationItem
}
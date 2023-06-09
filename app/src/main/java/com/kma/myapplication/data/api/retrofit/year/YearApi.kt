package com.kma.myapplication.data.api.retrofit.year

import com.kma.myapplication.data.model.Year
import retrofit2.http.GET

interface YearApi {
    @GET("/year")
    suspend fun getListYear(
    ): List<Year>?
}
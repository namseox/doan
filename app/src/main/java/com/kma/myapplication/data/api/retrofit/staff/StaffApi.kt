package com.kma.myapplication.data.api.retrofit.staff

import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.data.model.StaffItem
import retrofit2.http.GET

interface StaffApi {
    @GET("/user?search=&sort=desc&sortColumn=")
    suspend fun getStaff(
    ):List<StaffItem>
}
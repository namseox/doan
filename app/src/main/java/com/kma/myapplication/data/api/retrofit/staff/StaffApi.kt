package com.kma.myapplication.data.api.retrofit.staff

import com.kma.myapplication.data.model.CreateUser
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface StaffApi {
    @GET("/user?search=&sort=desc&sortColumn=")
    suspend fun getListStaff(
    ): List<StaffItem>
    @GET("/user/{id}")
    suspend fun getStaff(
        @Path("id") id: Int,
        @Query("year_id") year_id :Int
    ): UserX

    @DELETE("/user/{id}")
    suspend fun deleteStaff(@Path("id") i: Int): Int


//    @Multipart
    @POST("/user")
    suspend fun creatStaff(
    @Body userX: UserX
    ):CreateUser

    @PUT("/user/{id}")
    suspend fun updateStaff(
        @Path("id")i:Int,
        @Body userX: UserX
    ):StaffItem
}
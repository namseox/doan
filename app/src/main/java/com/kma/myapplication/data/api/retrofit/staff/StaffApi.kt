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

interface StaffApi {
    @GET("/user?search=&sort=desc&sortColumn=")
    suspend fun getStaff(
    ): List<StaffItem>


    @DELETE("/user/{id}")
    suspend fun deleteStaff(@Path("id") i: Int): Int


//    @Multipart
    @POST("/user")
    suspend fun creatStaff(
    @Body userX: UserX
//        @Part("department_id") id: Int,
//        @Part("name") name: String,
//        @Part("email") email: String,
//        @Part("password") password: String,
//        @Part("code") code: String,
//        @Part("birthday") birthday: Date,
//        @Part("position") position: String,
//        @Part("degree") degree: String,
//        @Part("number_salary") number_salary: Int,
//        @Part("income") income: Int,
//        @Part("avatar") avatar: String
    ):CreateUser

    @PUT("/user/{id}")
    suspend fun updateStaff(
        @Path("id")i:Int,
        @Body userX: UserX
    ):StaffItem
}
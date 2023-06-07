package com.kma.myapplication.data.api.retrofit.room

import com.kma.myapplication.data.model.ListRoomItem
import com.kma.myapplication.data.model.RoomItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RoomApi {
    @GET("/room")
    suspend fun getListListRoom(
    ): List<ListRoomItem>

    @GET("/room/{id}")
    suspend fun getItemRoom(@Path("id") i: Int): RoomItem

    @DELETE("/room/{id}")
    suspend fun deleteRoom(@Path("id") i: Int): Int

    @POST("/room")
    suspend fun creatMark(
        @Body itemRoom: ListRoomItem
    ): ListRoomItem

    @PUT("/room/{id}")
    suspend fun updateMark(
        @Path("id") i: Int,
        @Body itemRoom: ListRoomItem
    ): ListRoomItem
}
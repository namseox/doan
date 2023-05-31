package com.kma.myapplication.data.api.retrofit.book

import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListBookItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BookApi {
    @GET("/book")
    suspend fun getListBook(
    ): List<ListBookItem>

    @GET("/book/{id}")
    suspend fun getItemBook(@Path("id") i: Int): Book

    @DELETE("/book/{id}")
    suspend fun deleteBook(@Path("id") i: Int): Int

    @POST("/book")
    suspend fun creatBook(
        @Body itemBook: ListBookItem
    ): ListBookItem

    @PUT("/book/{id}")
    suspend fun updateBook(
        @Path("id") i: Int,
        @Body itemBook: ListBookItem
    ): ListBookItem
}
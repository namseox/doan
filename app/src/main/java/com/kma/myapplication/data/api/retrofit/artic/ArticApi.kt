package com.kma.myapplication.data.api.retrofit.artic


import com.kma.myapplication.data.model.ArticleItem
import com.kma.myapplication.data.model.ListArticleItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ArticApi {
    @GET("/article")
    suspend fun getListArticle(
    ): List<ListArticleItem>

    @GET("/article/{id}")
    suspend fun getItemArticle(@Path("id") i: Int): ArticleItem

    @DELETE("/article/{id}")
    suspend fun deleteArticle(@Path("id") i: Int): Int

    @POST("/article")
    suspend fun creatArticle(
        @Body itemArticle: ListArticleItem
    ): ListArticleItem

    @PUT("/article/{id}")
    suspend fun updateArticle(
        @Path("id") i: Int,
        @Body itemBook: ListArticleItem
    ): ListArticleItem
}
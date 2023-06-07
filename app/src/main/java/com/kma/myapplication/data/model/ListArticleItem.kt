package com.kma.myapplication.data.model

data class ListArticleItem(
    val code: String,
    val id: Int,
    val index_article: Int,
    val name: String,
    val num_person: Int,
    val open_access: String,
    val open_access_scopus: String,
    val total_time: Int,
    val type: Int,
    val year_id: Int
)
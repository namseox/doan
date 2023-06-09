package com.kma.myapplication.data.model

data class ArticleItem(
    val code: String="",
    val id: Int=0,
    val index_article: Int=0,
    val name: String="",
    val num_person: Int=0,
    val open_access: String="",
    val open_access_scopus: Int=0,
    val total_time: Int=0,
    val type: Int=0,
    val users: List<UserXX> = listOf(),
    val year: Year=Year(),
    val year_id: Int=0
)
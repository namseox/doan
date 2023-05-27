package com.kma.myapplication.data.model

data class Book(
    val code: String,
    val id: Int,
    val name: String,
    val num_page: Int,
    val num_person: Int,
    val num_publish: Int,
    val success: Boolean = false,
    val total_time: Int,
    val type: Int,
    val year_id: Int,
    val role : String ="",
    val type_book:Int = 0

)
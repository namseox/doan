package com.kma.myapplication.data.model

data class Book(
    val code: String,
    val deletedAt: Any,
    val id: Int,
    val name: String,
    val num_page: String,
    val num_person: Int,
    val num_publish: String,
    val success: Boolean,
    val total_time: Int,
    val type: String,
    val year_id: String
)
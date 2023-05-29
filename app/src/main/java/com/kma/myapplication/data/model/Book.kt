package com.kma.myapplication.data.model

data class Book(
    val code: String,
    val createdAt: String,
    val deletedAt: Any,
    val id: Int,
    val name: String,
    val num_page: Int,
    val num_person: Int,
    val num_publish: Int,
    val total_time: Int,
    val type: Int,
    val updatedAt: String,
    val users: List<Any>,
    val year: Any,
    val year_id: Int
)
package com.kma.myapplication.data.model

data class ListBookItem(
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
    val year_id: Int,
    val role:String="-1",
    val success: Boolean = false
)
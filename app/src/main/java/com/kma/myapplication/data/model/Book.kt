package com.kma.myapplication.data.model

data class Book(
    val code: String="",
    val id: Int= -1,
    val name: String= "",
    val num_page: Int= -1,
    val num_person: Int= -1,
    val num_publish: Int= -1,
    val total_time: Int = -1,
    val type: Int = -1,
    val updatedAt: String = "",
    val users: List<UserXX>,
//    val year: Int = -1,
    val years: Int = 2011,
    val year_id: Int = -1
)
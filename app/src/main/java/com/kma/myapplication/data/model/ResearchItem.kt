package com.kma.myapplication.data.model

data class ResearchItem(
    val acceptDate: String,
    val code: String,
    val endDate: String,
    val id: Int,
    val level: Int,
    val name: String,
    val num_person: Int,
    val result: Int,
    val startDate: String,
    val total_time: Int,
    val users: List<UserXX>,
    val year: Year,
    val year_id: Int
)
package com.kma.myapplication.data.model

data class TopicItemMainItem(
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
    val users: List<UserXXXX>,
    val year_id: Int
)
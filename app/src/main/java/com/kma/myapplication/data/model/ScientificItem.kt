package com.kma.myapplication.data.model

data class ScientificItem(
    val code: String,
    val date_decision: String,
    val name: String,
    val num_decision: String,
    val num_person: Int,
    val result_academy: Int,
    val result_level: Int,
    val total_time: Int,
    val type: Int,
    val users: List<UserXX>,
    val year: Year
)
package com.kma.myapplication.data.model

data class ThesisItem(
    val course: String,
    val id: Int,
    val name_student: String,
    val num_decision: String,
    val num_person: Int,
    val num_year: Int,
    val total_time: Int,
    val type: Int,
    val users: List<UserXX>,
    val year: Year,
    val year_id: Int
)
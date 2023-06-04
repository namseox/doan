package com.kma.myapplication.data.model

data class RoomItem(
    val code: String,
    val endDate: Any,
    val factor: Double,
    val id: Int,
    val name: Any,
    val num_exam_session: Int,
    val num_student: Int,
    val semester: Int,
    val startDate: String,
    val subject_id: Int,
    val time: Int,
    val type: Any,
    val user_id: Int,
    val year: Year,
    val year_id: Int
)
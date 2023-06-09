package com.kma.myapplication.data.model

data class RoomItem(
    val code: String="",
    val endDate: String="",
    val factor: Double= 0.0,
    val id: Int=0,
    val name: String="",
    val num_exam_session: Int=0,
    val num_student: Int=0,
    val semester: Int=0,
    val startDate: String="",
    val subject_id: Int=0,
    val time: Int=0,
    val type: Int=0,
    val user_id: Int=0,
    val year: Year=Year(),
    val year_id: Int=0
)
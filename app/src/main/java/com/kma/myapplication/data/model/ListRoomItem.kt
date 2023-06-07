package com.kma.myapplication.data.model

data class ListRoomItem(
    val code: String,
    val endDate: Any,
    val factor: Double,
    val id: Int,
    val name: String,
    val num_exam_session: Int,
    val num_student: Int,
    val semester: Int,
    val startDate: String,
    val subject: SubjectItem,
    val subject_id: Int,
    val time: Int,
    val type: String,
    val user: UserXX,
    val user_id: Int,
    val year_id: Int
)
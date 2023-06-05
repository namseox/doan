package com.kma.myapplication.data.model

data class ListMarkItem(
    val date_exam: String,
    val factor: Double,
    val id: Int,
    val num_exam: Int,
    val semester: Int,
    val subject: SubjectItem,
    val subject_id: Int,
    val type: Int,
    val user_id: Int,
    val year_id: Int,
    val user:UserX
)
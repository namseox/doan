package com.kma.myapplication.data.model

data class ExamItem(
    val code: String,
    val factor: Double,
    val id: Int,
    val name: Any,
    val num_exam: Int,
    val num_question: Int,
    val semester: Int,
    val subject_id: Int,
    val type: Int,
    val user_id: Int,
    val year: Year,
    val year_id: Int
)
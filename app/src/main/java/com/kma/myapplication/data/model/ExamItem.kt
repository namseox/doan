package com.kma.myapplication.data.model

data class ExamItem(
    val code: String="",
    val factor: Double=0.0,
    val id: Int=0,
    val name: String="",
    val num_exam: Int=0,
    val num_question: Int=0,
    val semester: Int=0,
    val subject: String="",
    val subject_id: Int=0,
    val type: Int=0,
    val user: UserXXX=UserXXX(),
    val user_id: Int=0,
    val year: Year=Year(),
    val year_id: Int=0
)
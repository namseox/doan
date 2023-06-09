package com.kma.myapplication.data.model

data class MarkItem(
    val date_exam: String="",
    val factor: Double=0.0,
    val form_mark: Int=0,
    val id: Int=0,
    val num_exam: Int=0,
    val semester: Int=0,
    val subject: SubjectItem=SubjectItem(),
    val subject_id: Int=0,
    val type: Int=0,
    val user: UserXX=UserXX(),
    val user_id: Int=0,
    val year_id: Int=0
)
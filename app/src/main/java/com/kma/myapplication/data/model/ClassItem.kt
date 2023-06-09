package com.kma.myapplication.data.model

data class ClassItem(
    val classroom: String="",
    val code: String="",
    val endDate: String="",
    val exam_create: String="",
    val exam_supervision: String="",
    val form_exam: Int=0,
    val form_teach: String="",
    val id: Int=0,
    val marking: String="",
    val name: String="",
    val num_credit: Int=0,
    val num_lesson: Int=0,
    val num_student: Int=0,
    val semester: Int=0,
    val startDate: String="",
    val subject_id: Int=0,
    val user: UserXX=UserXX(),
    val user_id: Int=0,
    val year: Year=Year(),
    val year_id: Int=0
)
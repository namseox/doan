package com.kma.myapplication.data.model

data class ListClassItem(
    val code: String,
    val endDate: String,
    val exam_create: Any,
    val exam_supervision: Any,
    val form_exam: Int,
    val form_teach: String,
    val id: Int,
    val marking: Any,
    val name: String,
    val num_credit: Int,
    val num_lesson: Int,
    val num_student: Int,
    val semester: String,
    val startDate: String
)
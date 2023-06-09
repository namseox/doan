package com.kma.myapplication.data.model

data class DashboardClass(
    val classroom: String,
    val code: String,
    val endDate: String,
//    val exam_create: String,
//    val exam_supervision: String,
    val form_exam: Int,
    val form_teach: String,
    val id: Int,
//    val level_teach: String,
//    val marking: String,
    val name: String,
    val num_credit: Int,
    val num_lesson: Int,
    val num_student: Int,
//    val parent_id: Int,
    val semester: String,
    val startDate: String,
    val subject_id: Int,
//    val time_teach: String,
    val user_id: Int,
    val year_id: Int
)
package com.kma.myapplication.data.model

data class UserX(
    val avatar: Any,
    val birthday: String,
    val code: String,
    val degree: String,
    val department_id: Int,
    val email: String,
    val id: Int,
    val income: Int,
    val name: String,
    val number_salary: Int,
    val password: String="1",
    val position: String,
    val salt: String,
    val time_per_year: Int,
    val time_reserve: Int,
    val timeScient: Int=0,
    val timeTeach: Int=0,
    val departmentName: String="",

)
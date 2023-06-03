package com.kma.myapplication.data.model

data class CreateUser(
    val birthday: String,
    val code: String,
    val degree: String,
    val department_id: String,
    val email: String,
    val id: Int,
    val income: String,
    val name: String,
    val number_salary: String,
    val password: String,
    val position: String,
    val salt: String,
    val success: Boolean,
    val time_per_year: Int,
    val time_reserve: Int,
)
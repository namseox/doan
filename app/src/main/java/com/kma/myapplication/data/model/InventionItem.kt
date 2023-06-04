package com.kma.myapplication.data.model

data class InventionItem(
    val code: String,
    val date_recognition: String,
    val id: Int,
    val level: Int,
    val name: String,
    val num_person: Int,
    val number_recognition: String,
    val total_time: Int,
    val users: List<UserXX>,
    val year: Year,
    val year_id: Int
)
package ru.ahoy.uni.models

data class Schedule(
    val subjects: List<Subject> = listOf(),
    val creator_id: String = "",
    val date_create: String = ""
)
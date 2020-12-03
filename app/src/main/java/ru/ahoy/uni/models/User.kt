package ru.ahoy.uni.models

data class User(
    val id: String = "",
    var username: String = "",
    var schedule_id: String = "",
    var photo_url: String = ""
)
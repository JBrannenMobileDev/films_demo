package com.example.filmdemo.data.model.entity

data class Films(
    val count: Int,
    val next: Int?,
    val previous: Int?,
    val results: List<Film>,
)

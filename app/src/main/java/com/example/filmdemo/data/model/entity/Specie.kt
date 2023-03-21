package com.example.filmdemo.data.model.entity

import androidx.room.PrimaryKey

data class Specie(
    @PrimaryKey val url: String,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val skinColors: String,
    val hairColors: String,
    val eyeColors: String,
    val averageLifespan: String,
    val homeworld: String,
    val language: String,
    val people: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
)

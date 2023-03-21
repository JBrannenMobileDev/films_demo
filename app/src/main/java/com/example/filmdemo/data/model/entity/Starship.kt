package com.example.filmdemo.data.model.entity

import androidx.room.PrimaryKey

data class Starship(
    @PrimaryKey val url: String,
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: String,
    val consumables: String,
    val hyperdriveRating: String,
    val mglt: String,
    val starshipClass: String,
    val pilots: List<Any?>,
    val films: List<String>,
    val created: String,
    val edited: String,
)

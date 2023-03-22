package com.example.filmdemo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmdemo.data.db.dao.TableNames

@Entity(tableName = TableNames.TABLE_VEHICLE)
data class Vehicle(
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
    val vehicleClass: String,
    val pilots: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
)

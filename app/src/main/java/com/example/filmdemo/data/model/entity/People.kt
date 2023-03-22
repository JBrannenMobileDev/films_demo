package com.example.filmdemo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmdemo.data.db.dao.TableNames

@Entity(tableName = TableNames.TABLE_PEOPLE)
data class People(
    @PrimaryKey val url: String,
    val name: String,
    val height: String?,
    val mass: String?,
    val hairColor: String?,
    val skinColor: String?,
    val eyeColor: String?,
    val birthYear: String?,
    val gender: String?,
    val homeworld: String?,
    val films: List<String>?,
    val species: List<String>?,
    val vehicles: List<String>?,
    val starships: List<String>?,
    val created: String?,
    val edited: String?,
)

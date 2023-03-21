package com.example.filmdemo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmdemo.data.db.dao.TableNames

@Entity(tableName = TableNames.TABLE_FILM)
data class Film(
    @PrimaryKey val url: String,
    val title: String,
    val episodeId: Long,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
    val created: String,
    val edited: String,
)

//GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
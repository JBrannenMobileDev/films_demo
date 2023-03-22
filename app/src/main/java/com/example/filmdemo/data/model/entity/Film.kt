package com.example.filmdemo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmdemo.data.db.dao.TableNames
import com.google.gson.annotations.SerializedName

@Entity(tableName = TableNames.TABLE_FILM)
data class Film(
    @PrimaryKey val url: String,
    val title: String,
    @SerializedName("episode_id")
    val episodeId: Long,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val director: String,
    val producer: String,
    @SerializedName("release_date")
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
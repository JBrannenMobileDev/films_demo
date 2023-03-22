package com.example.filmdemo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmdemo.data.db.dao.FilmsDao
import com.example.filmdemo.data.db.dao.TableNames

@Entity(tableName = TableNames.TABLE_FILMS)
data class Films(
    @PrimaryKey
    var id : String = FilmsDao.FILMS_SINGLE_ITEM_KEY,
    val count: Int,
    val next: Int?,
    val previous: Int?,
    val results: List<Film>,
)

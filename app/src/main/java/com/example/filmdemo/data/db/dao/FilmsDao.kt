package com.example.filmdemo.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.filmdemo.data.model.entity.Films

@Dao
interface FilmsDao {
    companion object {
        const val FILMS_SINGLE_ITEM_KEY = "films_single_item"
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: Films)
    @Update
    suspend fun update(film: Films)

    @Delete
    suspend fun delete(film: Films)
    @Query("SELECT * FROM ${TableNames.TABLE_FILMS} WHERE id = :singletonId")
    suspend fun getFilmsSingleItem(singletonId: String) : Films?
}
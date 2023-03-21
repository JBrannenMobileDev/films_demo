package com.example.filmdemo.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.filmdemo.data.model.entity.Film

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(films: List<Film>)

    @Update
    suspend fun update(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Query("DELETE FROM ${TableNames.TABLE_FILM}")
    fun deleteAll()

    @Query("SELECT * FROM ${TableNames.TABLE_FILM} WHERE id = :id")
    fun getById(id: String): Film

    @Query("SELECT * FROM ${TableNames.TABLE_FILM}")
    fun pagingSource(): PagingSource<Int, Film>
}
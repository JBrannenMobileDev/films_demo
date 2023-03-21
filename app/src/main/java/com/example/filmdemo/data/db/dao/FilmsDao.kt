package com.example.filmdemo.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.filmdemo.data.model.entity.Films

@Dao
interface FilmsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: Films)

    @Update
    suspend fun update(film: Films)

    @Delete
    suspend fun delete(film: Films)

    @Query("DELETE FROM ${TableNames.TABLE_FILMS}")
    fun deleteAll()

    @Query("SELECT * FROM ${TableNames.TABLE_FILMS} WHERE id = :id")
    fun getById(id: String): Films

    @Query("SELECT * FROM ${TableNames.TABLE_FILM}")
    fun pagingSource(): PagingSource<Int, Films>
}
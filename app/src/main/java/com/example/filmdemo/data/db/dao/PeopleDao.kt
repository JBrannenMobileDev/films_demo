package com.example.filmdemo.data.db.dao

import androidx.room.*
import com.example.filmdemo.data.model.entity.People
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(people: People)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<People>)

    @Update
    suspend fun update(people: People)

    @Delete
    suspend fun delete(people: People)

    @Query("DELETE FROM ${TableNames.TABLE_PEOPLE}")
    fun deleteAll()

    @Query("SELECT * FROM ${TableNames.TABLE_PEOPLE} WHERE url = :id")
    fun getById(id: String): People

    @Query("SELECT * FROM ${TableNames.TABLE_PEOPLE}")
    fun getAllFlow(): Flow<List<People>>
}
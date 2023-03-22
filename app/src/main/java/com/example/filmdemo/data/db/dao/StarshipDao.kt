package com.example.filmdemo.data.db.dao

import androidx.room.*
import com.example.filmdemo.data.model.entity.Starship

@Dao
interface StarshipDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(people: Starship)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<Starship>)

    @Update
    suspend fun update(people: Starship)

    @Delete
    suspend fun delete(people: Starship)

    @Query("DELETE FROM ${TableNames.TABLE_STARSHIP}")
    fun deleteAll()

    @Query("SELECT * FROM ${TableNames.TABLE_STARSHIP} WHERE url = :id")
    suspend fun getById(id: String): Starship

    @Query("SELECT * FROM ${TableNames.TABLE_STARSHIP} WHERE url IN (:ids)")
    suspend fun getAllMatching(ids: List<String?>?): List<Starship>
}
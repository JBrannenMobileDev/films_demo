package com.example.filmdemo.data.repository

import com.example.filmdemo.data.db.AppDatabase
import com.example.filmdemo.data.db.dao.PeopleDao
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.data.remote.retrofit.FilmApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val personDao: PeopleDao,
    private val database: AppDatabase,
    private val filmsDataSource: FilmApiService,
) : Repository<People> {
    override suspend fun save(item: People) {
        personDao.insert(item)
    }

    override suspend fun get(id: String): People {
        return personDao.getById(id)
    }

    fun getAll(urlList : List<String>): Flow<List<People>> {
        return personDao.getAllFlow()
    }

    override suspend fun deleteAll() {
        personDao.deleteAll()
    }

    override suspend fun delete(item: People) {
        personDao.delete(item)
    }

}
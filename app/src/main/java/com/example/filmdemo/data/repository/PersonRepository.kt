package com.example.filmdemo.data.repository

import com.example.filmdemo.data.db.dao.PeopleDao
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.data.remote.retrofit.FilmApiService
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val personDao: PeopleDao,
    private val filmsDataSource: FilmApiService,
) : Repository<People> {
    override suspend fun save(item: People) {
        personDao.insert(item)
    }

    override suspend fun get(id: String): People {
        return personDao.getById(id)
    }

    suspend fun getAllMatching(urlList : List<String>): List<People>? {
        return try {
            var result = personDao.getAllMatchingFlow(urlList)
            if(result.size < urlList.size) {
                urlList.forEach { url ->
                    val personId = parseIdFromUrl(url)
                    if(personId != null) {
                        personDao.insert(filmsDataSource.getPerson(personId))
                    }
                }
                result = personDao.getAllMatchingFlow(urlList)
            }
            result
        } catch (ex : Exception) {
            null
        }
    }

    private fun parseIdFromUrl(url: String): Int? {
        val items = url.split("/")
        return try {
            items[items.size-2].toInt()
        } catch (ex : java.lang.NumberFormatException) {
            null
        }
    }

    override suspend fun deleteAll() {
        personDao.deleteAll()
    }

    override suspend fun delete(item: People) {
        personDao.delete(item)
    }

}
package com.example.filmdemo.data.repository

import com.example.filmdemo.data.db.dao.StarshipDao
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.data.model.entity.Starship
import com.example.filmdemo.data.remote.retrofit.FilmApiService
import javax.inject.Inject

class StarshipsRepository @Inject constructor(
    private val starshipDao: StarshipDao,
    private val filmsDataSource: FilmApiService,
) : Repository<Starship> {
    override suspend fun save(item: Starship) {
        starshipDao.insert(item)
    }

    override suspend fun get(id: String): Starship {
        return starshipDao.getById(id)
    }

    suspend fun getAllMatching(urlList : List<String>): List<Starship>? {
        return try {
            var result = starshipDao.getAllMatching(urlList)
            if(result.size < urlList.size) {
                urlList.forEach { url ->
                    val starshipId = parseIdFromUrl(url)
                    if(starshipId != null) {
                        starshipDao.insert(filmsDataSource.getStarship(starshipId))
                    }
                }
                result = starshipDao.getAllMatching(urlList)
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
        starshipDao.deleteAll()
    }

    override suspend fun delete(item: Starship) {
        starshipDao.delete(item)
    }

}
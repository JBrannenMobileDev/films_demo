package com.example.filmdemo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.filmdemo.data.db.AppDatabase
import com.example.filmdemo.data.db.dao.FilmDao
import com.example.filmdemo.data.db.dao.FilmsDao
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.model.entity.Films
import com.example.filmdemo.data.remote.retrofit.FilmApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val filmsDao: FilmsDao,
    private val filmDao: FilmDao,
    private val database: AppDatabase,
    private val filmsDataSource: FilmApiService,
) : Repository<Film> {
    override suspend fun save(item: Film) {
        filmDao.insert(item)
    }

    override suspend fun get(id: String): Film {
        return filmDao.getById(id)
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getAllPaged(): Flow<PagingData<Film>> {
        val films = filmsDataSource.getAllFilms(1)
        val filmsDao = database.filmsDao()
        return Pager(
            config = PagingConfig(pageSize = 50),
            remoteMediator = FilmRemoteMediator(films.count, films.next, database, filmsDataSource)
        ) {
            filmDao.pagingSource()
        }.flow
    }

    override suspend fun deleteAll() {
        filmDao.deleteAll()
    }

    override suspend fun delete(item: Film) {
        filmDao.delete(item)
    }

}
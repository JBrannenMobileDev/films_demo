package com.example.filmdemo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.filmdemo.data.db.AppDatabase
import com.example.filmdemo.data.db.dao.FilmDao
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.model.entity.Films
import com.example.filmdemo.data.remote.retrofit.FilmApiService
import com.example.filmdemo.data.repository.mediators.FilmRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmRepository @Inject constructor(
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
    suspend fun getAllPaged(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = FilmRemoteMediator(database, filmsDataSource)
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
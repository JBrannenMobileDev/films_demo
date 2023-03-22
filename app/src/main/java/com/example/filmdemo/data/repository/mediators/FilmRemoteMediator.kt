package com.example.filmdemo.data.repository.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.filmdemo.data.db.AppDatabase
import com.example.filmdemo.data.db.dao.FilmsDao
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.model.entity.Films
import com.example.filmdemo.data.remote.retrofit.FilmApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class FilmRemoteMediator(
    private val database: AppDatabase,
    private val filmApiService: FilmApiService
) : RemoteMediator<Int, Film>() {
    private val filmDao = database.filmDao()
    private val filmsDao = database.filmsDao()
    private var next: Int? = null
    private var count = 0

    override suspend fun initialize(): InitializeAction {
        var itemCount = 0
        var expectedItemCount : Int? = null

        database.withTransaction {
            itemCount = filmDao.getCount()
            expectedItemCount = filmsDao.getFilmsSingleItem(FilmsDao.FILMS_SINGLE_ITEM_KEY)?.count
        }

        if(expectedItemCount == null) return InitializeAction.LAUNCH_INITIAL_REFRESH
        return if (itemCount >= expectedItemCount!!)
        {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Film>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true) // Not needed
                LoadType.APPEND -> {
                    state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    if(next != null) {
                        next!! + 1
                    } else {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                }
            }

            var response: Films? = null

            if(loadType == LoadType.REFRESH) {
                database.withTransaction {
                    filmDao.deleteAll()
                }
                response = filmApiService.getFilmsByPage(1)
            }

            if(loadType == LoadType.APPEND) {
                response = loadKey?.let {
                    filmApiService.getFilmsByPage(it)
                } ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            if(response != null) {
                database.withTransaction {
                    response.id = FilmsDao.FILMS_SINGLE_ITEM_KEY
                    filmsDao.insert(response)
                    filmDao.insertAll(response.results)
                    next = response.next
                    count = response.count
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = response?.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
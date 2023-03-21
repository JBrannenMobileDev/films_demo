package com.example.filmdemo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.filmdemo.data.db.AppDatabase
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.remote.retrofit.FilmApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class FilmRemoteMediator(
    private val count: Int,
    private val next: Int?,
    private val database: AppDatabase,
    private val filmApiService: FilmApiService
) : RemoteMediator<Int, Film>() {
    private val filmDao = database.filmDao()

    override suspend fun initialize(): InitializeAction {
        return if (listOf(filmDao.pagingSource()).size == count)
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
                        next + 1
                    } else {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                }
            }

            val response = loadKey?.let {
                filmApiService.getAllFilms(it)
            } ?: return MediatorResult.Success(endOfPaginationReached = true)

            database.withTransaction {
                filmDao.insertAll(response.results)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
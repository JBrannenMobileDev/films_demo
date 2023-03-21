package com.example.filmdemo.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface Repository<T : Any> {
    suspend fun save(item: T)
    suspend fun get(id: String) : T
    suspend fun getAllPaged() : Flow<PagingData<T>>
    suspend fun delete(item: T)
    suspend fun deleteAll()
}
package com.example.filmdemo.data.remote.retrofit

import com.example.filmdemo.data.model.entity.Films
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApiService {
    @GET("films/")
    suspend fun getAllFilms(
        @Query("page") page : Int
    ) : Films
}
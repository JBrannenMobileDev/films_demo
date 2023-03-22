package com.example.filmdemo.data.remote.retrofit

import com.example.filmdemo.data.model.entity.Films
import com.example.filmdemo.data.model.entity.People
import com.example.filmdemo.data.model.entity.Starship
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApiService {
    @GET("films/?")
    suspend fun getFilmsByPage(@Query("page") page : Int) : Films

    @GET("people/{id}")
    suspend fun getPerson(@Path("id") id : Int) : People

    @GET("starships/{id}")
    suspend fun getStarship(@Path("id") id : Int) : Starship
}
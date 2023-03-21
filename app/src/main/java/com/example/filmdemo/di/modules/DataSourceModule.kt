package com.example.filmdemo.di.modules

import com.example.filmdemo.data.remote.retrofit.FilmApiService
import com.example.filmdemo.data.remote.retrofit.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    fun provideFilmDataSource() : FilmApiService {
        return RetrofitBuilder.filmApiService
    }
}
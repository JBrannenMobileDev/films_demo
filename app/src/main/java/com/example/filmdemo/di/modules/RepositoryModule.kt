package com.example.filmdemo.di.modules

import com.example.filmdemo.data.db.AppDatabase
import com.example.filmdemo.data.db.dao.FilmDao
import com.example.filmdemo.data.db.dao.PeopleDao
import com.example.filmdemo.data.db.dao.StarshipDao
import com.example.filmdemo.data.remote.retrofit.FilmApiService
import com.example.filmdemo.data.repository.FilmRepository
import com.example.filmdemo.data.repository.CharactersRepository
import com.example.filmdemo.data.repository.StarshipsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun provideFilmRepository(filmDao: FilmDao, database: AppDatabase, filmDataSource: FilmApiService): FilmRepository {
        return FilmRepository(filmDao, database, filmDataSource)
    }

    @Provides
    fun provideCharacterRepository(peopleDao: PeopleDao, filmDataSource: FilmApiService): CharactersRepository {
        return CharactersRepository(peopleDao, filmDataSource)
    }

    @Provides
    fun provideStarshipsRepository(starshipsDao: StarshipDao, filmDataSource: FilmApiService): StarshipsRepository {
        return StarshipsRepository(starshipsDao, filmDataSource)
    }
}
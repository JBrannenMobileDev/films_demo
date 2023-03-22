package com.example.filmdemo.di.modules

import android.content.Context
import androidx.room.Room
import com.example.filmdemo.data.db.AppDatabase
import com.example.filmdemo.data.db.dao.FilmDao
import com.example.filmdemo.data.db.dao.PeopleDao
import com.example.filmdemo.data.db.dao.StarshipDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideFilmDao(database: AppDatabase): FilmDao {
        return database.filmDao()
    }

    @Provides
    fun providePeopleDao(database: AppDatabase): PeopleDao {
        return database.peopleDao()
    }

    @Provides
    fun provideStarshipsDao(database: AppDatabase): StarshipDao {
        return database.starshipDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "main.db"
        ).build()
    }
}
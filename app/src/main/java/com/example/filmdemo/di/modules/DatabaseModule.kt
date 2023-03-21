package com.example.filmdemo.di.modules

import android.content.Context
import androidx.room.Room
import com.example.filmdemo.data.db.AppDatabase
import com.example.filmdemo.data.db.dao.FilmDao
import com.example.filmdemo.data.db.dao.FilmsDao
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
    fun provideFilmsDao(database: AppDatabase): FilmsDao {
        return database.filmsDao()
    }

    @Provides
    fun provideFilmDao(database: AppDatabase): FilmDao {
        return database.filmDao()
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
package com.example.filmdemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.filmdemo.data.db.dao.FilmDao
import com.example.filmdemo.data.db.dao.FilmsDao
import com.example.filmdemo.data.model.entity.Film
import com.example.filmdemo.data.model.entity.Films

@Database(entities = [Film::class, Films::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmDao() : FilmDao
    abstract fun filmsDao() : FilmsDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "main.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
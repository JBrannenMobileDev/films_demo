package com.example.filmdemo.data.db

import androidx.room.TypeConverter
import com.example.filmdemo.data.model.entity.Film
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    companion object {

        @TypeConverter()
        @JvmStatic
        fun stringListFromString(value: String?): List<String> {
            val listType = object :
                TypeToken<List<String?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        @JvmStatic
        fun stringListToString(list: List<String?>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }

        @TypeConverter()
        @JvmStatic
        fun filmListFromString(value: String?): List<Film> {
            val listType = object :
                TypeToken<List<Film?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        @JvmStatic
        fun filmListToString(list: List<Film?>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
}
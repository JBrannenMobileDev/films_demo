package com.example.filmdemo.data.db

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromLocalDateTime(value: LocalDateTime): String {
            return value.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toLocalDateTime(value: String): LocalDateTime {
            return LocalDateTime.parse(value)
        }
    }
}
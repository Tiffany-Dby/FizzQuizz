package com.supdevinci.fizzquiz.utils

import androidx.room.TypeConverter
import java.util.Date
import kotlin.let

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}
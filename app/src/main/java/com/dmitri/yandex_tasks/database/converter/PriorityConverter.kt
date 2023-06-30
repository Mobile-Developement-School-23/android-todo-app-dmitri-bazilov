package com.dmitri.yandex_tasks.database.converter

import androidx.room.TypeConverter
import com.dmitri.yandex_tasks.util.entity.Priority

class PriorityConverter {

    @TypeConverter
    fun fromPriority(priority: Priority): String = priority.name

    @TypeConverter
    fun toPriority(value: String): Priority = Priority.valueOf(value)
}
package com.dmitri.yandex_tasks.database.converter

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {

    @TypeConverter
    fun fromUUID(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun toUUID(value: String): UUID = UUID.fromString(value)
}
package com.dmitri.yandex_tasks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dmitri.yandex_tasks.database.converter.PriorityConverter
import com.dmitri.yandex_tasks.database.converter.UUIDConverter
import com.dmitri.yandex_tasks.util.entity.Priority
import java.util.UUID

@Entity(tableName = TodoEntity.TABLE_NAME)
data class TodoEntity(
    @PrimaryKey
    @TypeConverters(UUIDConverter::class)
    @ColumnInfo(name = "id")
    val id: UUID,
    @ColumnInfo(name = "description")
    val description: String,
    @TypeConverters(PriorityConverter::class)
    @ColumnInfo(name = "priority")
    val priority: Priority,
    @ColumnInfo(name = "deadline")
    val deadline: Long?,
    @ColumnInfo(name = "done")
    val done: Boolean,
    @ColumnInfo(name = "creationDate")
    val creationDate: Long,
    @ColumnInfo(name = "modificationDate")
    val modificationDate: Long?
) {
    companion object {
        const val TABLE_NAME = "todo_table"
    }
}

package com.dmitri.yandex_tasks.util.entity

import android.graphics.drawable.Drawable
import java.time.LocalDate
import java.time.LocalDateTime

data class TodoItem(
    val id: String,
    val checkBox: Drawable,
    val description: String,
    val priority: Priority,
    val deadline: LocalDate?,
    val done: Boolean,
    val creationDate: LocalDate,
    val modificationDate: LocalDate?
)

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

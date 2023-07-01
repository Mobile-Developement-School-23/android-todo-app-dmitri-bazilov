package com.dmitri.yandex_tasks.util.entity

import java.io.Serializable
import java.time.LocalDate
import java.util.UUID

data class TodoItem(
    var id: UUID,
    var description: String,
    var priority: Priority,
    var deadline: LocalDate?,
    var done: Boolean,
    var creationDate: LocalDate,
    var modificationDate: LocalDate?
) : Serializable

enum class Priority(val text: String) {
    HIGH("Высокая"),
    MEDIUM("Средняя"),
    LOW("Нет")
}

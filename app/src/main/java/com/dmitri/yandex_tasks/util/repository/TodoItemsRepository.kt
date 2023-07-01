package com.dmitri.yandex_tasks.util.repository

import androidx.lifecycle.MutableLiveData
import com.dmitri.yandex_tasks.util.entity.Priority
import com.dmitri.yandex_tasks.util.entity.TodoItem
import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

class TodoItemsRepository {

    val todoList: MutableLiveData<MutableList<TodoItem>> = MutableLiveData()

    init {
        todoList.value = getItems()
    }

    private fun getItems(): MutableList<TodoItem> {
        return buildList {
            val description = "Test Todo" + (1..20).random().toString()

            val numberOfItems = 13
            for (i in 0 until numberOfItems)
                add(
                    TodoItem(
                        UUID.randomUUID(),
                        description,
                        Priority.values()[(0..2).random()],
                        null,
                        Random.nextBoolean(),
                        LocalDate.now(),
                        null
                    )
                )
        }.toMutableList()
    }

    fun insert(todoItem: TodoItem) {
        todoList.value!!.add(todoItem)
    }

    fun update(todoItem: TodoItem) {
        val todo = todoList.value!!.find { it.id == todoItem.id }
        todo!!.description = todoItem.description
        todo.priority = todoItem.priority
        todo.deadline = todoItem.deadline
        todo.modificationDate = todoItem.modificationDate

    }

    fun delete(todoItem: TodoItem) {
        todoList.value!!.remove(todoItem)
    }
}
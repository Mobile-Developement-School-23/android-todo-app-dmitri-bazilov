package com.dmitri.yandex_tasks.util.repository

import android.app.Application
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmitri.yandex_tasks.TaskApplication
import com.dmitri.yandex_tasks.database.AppDatabase
import com.dmitri.yandex_tasks.database.dao.TodoDao
import com.dmitri.yandex_tasks.database.entity.TodoEntity
import com.dmitri.yandex_tasks.util.entity.Priority
import com.dmitri.yandex_tasks.util.entity.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

class TodoItemsRepository(application: Application) {

    val todoList: List<TodoItem>
    val todoRoomDao: TodoDao

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        todoRoomDao = database.todoDao()
        todoList = listOf()
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

    fun List<TodoEntity>.toItemView(): List<TodoItem> = this.map { it.toItemView() }


    fun TodoEntity.toItemView(): TodoItem = TodoItem(
        id = id,
        description = description,
        priority = priority,
        deadline = deadline?.let { LocalDate.ofEpochDay(it) },
        done = done,
        creationDate = LocalDate.ofEpochDay(creationDate),
        modificationDate = modificationDate?.let { LocalDate.ofEpochDay(it) }
    )

    fun TodoItem.toEntity() = TodoEntity(
        id = id,
        description = description,
        priority = priority,
        deadline = deadline?.toEpochDay(),
        done = done,
        creationDate = creationDate.toEpochDay(),
        modificationDate = modificationDate?.toEpochDay()
    )


    suspend fun fetchTodoList(): List<TodoItem> {
        return todoRoomDao.getAllTodos().map { it.toItemView() }
    }

    suspend fun insert(todoItem: TodoItem) {
        return todoRoomDao.insertTodoItem(todoItem.toEntity())
    }

    suspend fun update(todoItem: TodoItem) {
        todoRoomDao.updateTodoItem(todoItem.toEntity())
    }

    suspend fun delete(todoItem: TodoItem) {
        todoRoomDao.deleteTodoItem(todoItem.toEntity())
    }

    suspend fun changeDoneStatus(todoItem: TodoItem) {

    }
}
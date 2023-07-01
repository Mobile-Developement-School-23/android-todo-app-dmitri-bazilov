package com.dmitri.yandex_tasks.util.repository

import android.app.Application
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmitri.yandex_tasks.TaskApplication
import com.dmitri.yandex_tasks.database.AppDatabase
import com.dmitri.yandex_tasks.database.dao.TodoDao
import com.dmitri.yandex_tasks.database.entity.TodoEntity
import com.dmitri.yandex_tasks.network.NetworkUtil
import com.dmitri.yandex_tasks.network.entity.TodoApiItem
import com.dmitri.yandex_tasks.network.retrofit.ApiClient
import com.dmitri.yandex_tasks.network.retrofit.TodoApi
import com.dmitri.yandex_tasks.util.entity.Priority
import com.dmitri.yandex_tasks.util.entity.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import java.lang.Exception
import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

class TodoItemsRepository(application: Application) {

    val todoList: List<TodoItem>
    val todoRoomDao: TodoDao
    val todoRetrofitApi: TodoApi

    val util: NetworkUtil

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        todoRoomDao = database.todoDao()
        todoList = listOf()
        todoRetrofitApi = ApiClient().api
        util = NetworkUtil(application)
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

    fun TodoItem.toApiItem(lastUpdatedBy: String = "name") = TodoApiItem(
        id = id.toString(),
        description = description,
        priority = priority.apiRepr,
        deadline = deadline?.toEpochDay(),
        done = done,
        creationDate = creationDate.toEpochDay(),
        modificationDate = modificationDate?.toEpochDay() ?: LocalDate.now().toEpochDay(),
        color = null,
        lastUpdatedBy = lastUpdatedBy
    )

    fun TodoApiItem.toItemView() = TodoItem(
        id = UUID.fromString(id),
        description = description,
        priority = when (priority) {
            "low" -> Priority.LOW
            "basic" -> Priority.MEDIUM
            else -> Priority.HIGH
        },
        deadline = LocalDate.ofEpochDay(creationDate),
        done = done,
        creationDate = LocalDate.ofEpochDay(creationDate),
        modificationDate = LocalDate.ofEpochDay(creationDate)
    )


    suspend fun fetchTodoList(): List<TodoItem> {
        return todoRoomDao.getAllTodos().map { it.toItemView() }
    }

    suspend fun getItemsFromServer() {
        //todo error handle
        try {
            val response = todoRetrofitApi.getList()
            if (response.isSuccessful) {
                todoRoomDao.insertItems(
                    response.body()?.list?.map { it.toItemView().toEntity() } ?: listOf()
                )
                response.body()?.let { util.setRevision(it.revision) }
            } else {
                throw Exception()
            }
        } catch (ex: Exception) {

        }
    }

    suspend fun syncWithServerList() {}


    suspend fun insert(todoItem: TodoItem) {
        todoRoomDao.insertTodoItem(todoItem.toEntity())
        insertInServer(todoItem)
    }

    private fun insertInServer(todoItem: TodoItem) {

    }

    suspend fun update(todoItem: TodoItem) {
        todoRoomDao.updateTodoItem(todoItem.toEntity())
        updateOnServer(todoItem)
    }

    private fun updateOnServer(todoItem: TodoItem) {

    }

    suspend fun deleteItem(todoItem: TodoItem) {
        todoRoomDao.deleteTodoItem(todoItem.toEntity())
        deleteItemOnServer(todoItem)
    }

    private fun deleteItemOnServer(todoItem: TodoItem) {

    }


    suspend fun changeDoneStatus(todoItem: TodoItem) {
        update(todoItem.copy(done = !todoItem.done, modificationDate = LocalDate.now()))
    }
}
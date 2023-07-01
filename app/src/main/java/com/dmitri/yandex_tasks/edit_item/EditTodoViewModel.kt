package com.dmitri.yandex_tasks.edit_item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dmitri.yandex_tasks.TaskApplication
import com.dmitri.yandex_tasks.util.entity.TodoItem
import com.dmitri.yandex_tasks.util.repository.TodoItemsRepository

class EditTodoViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TodoItemsRepository = (application as TaskApplication).repository

    suspend fun insert(todoItem: TodoItem) {
        repository.insert(todoItem)
    }

    suspend fun update(todoItem: TodoItem) {
        repository.update(todoItem)
    }

    suspend fun delete(todoItem: TodoItem) {
        repository.deleteItem(todoItem)
    }
}
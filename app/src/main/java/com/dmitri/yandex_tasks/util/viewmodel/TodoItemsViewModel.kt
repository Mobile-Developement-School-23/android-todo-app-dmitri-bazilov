package com.dmitri.yandex_tasks.util.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.dmitri.yandex_tasks.TaskApplication
import com.dmitri.yandex_tasks.util.entity.TodoItem
import com.dmitri.yandex_tasks.util.repository.TodoItemsRepository

class TodoItemsViewModel(application: Application): AndroidViewModel(application) {

    var repository: TodoItemsRepository

    init {
        repository = (application as TaskApplication).repository
    }

    fun insert(todoItem: TodoItem) {
        repository.insert(todoItem)
    }

    fun update(todoItem: TodoItem) {
        repository.update(todoItem)
    }

    fun delete(todoItem: TodoItem) {
        repository.delete(todoItem)
    }
}
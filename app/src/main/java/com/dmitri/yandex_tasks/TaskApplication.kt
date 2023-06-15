package com.dmitri.yandex_tasks

import android.app.Application
import com.dmitri.yandex_tasks.util.repository.TodoItemsRepository

class TaskApplication : Application() {

    lateinit var todoItemsRepository: TodoItemsRepository

    override fun onCreate() {
        super.onCreate()
        todoItemsRepository = TodoItemsRepository()
    }
}
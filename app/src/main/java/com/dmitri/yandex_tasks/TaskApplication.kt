package com.dmitri.yandex_tasks

import android.app.Application
import com.dmitri.yandex_tasks.util.repository.TodoItemsRepository

class TaskApplication : Application() {

    val repository = TodoItemsRepository()
    var showChecked: Boolean = true

    override fun onCreate() {
        super.onCreate()
    }
}
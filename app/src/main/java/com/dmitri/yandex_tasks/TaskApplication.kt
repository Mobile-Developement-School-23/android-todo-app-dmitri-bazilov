package com.dmitri.yandex_tasks

import android.app.Application
import com.dmitri.yandex_tasks.util.repository.TodoItemsRepository

class TaskApplication : Application() {
    //todo appbar colorimportance
    val repository = TodoItemsRepository()

    override fun onCreate() {
        super.onCreate()
    }
}
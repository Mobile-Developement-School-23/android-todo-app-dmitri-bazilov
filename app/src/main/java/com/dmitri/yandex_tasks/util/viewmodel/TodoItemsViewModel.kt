package com.dmitri.yandex_tasks.util.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.util.entity.TodoItem

class TodoItemsViewModel: ViewModel() {

    var todoItems = MutableLiveData<MutableList<TodoItem>>()

    init {
    }
}
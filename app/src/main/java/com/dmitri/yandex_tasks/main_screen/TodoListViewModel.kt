package com.dmitri.yandex_tasks.main_screen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.dmitri.yandex_tasks.TaskApplication
import com.dmitri.yandex_tasks.main_screen.recycler.TodoItemsAdapter
import com.dmitri.yandex_tasks.util.entity.TodoItem
import com.dmitri.yandex_tasks.util.repository.TodoItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoItemsRepository = (application as TaskApplication).repository
    private val showDoneFlag: Boolean = (application as TaskApplication).showChecked
    val todoListAdapter: TodoItemsAdapter = getListAdapter()
    val todoList: List<TodoItem> = repository.todoList

    private fun getListAdapter(): TodoItemsAdapter = TodoItemsAdapter(
        onCheckboxSwitch = {todoItem ->
            viewModelScope.launch(Dispatchers.IO) { repository.changeDoneStatus(todoItem) }
        },
        onItemClick = {todoItem, itemView ->
            Navigation.findNavController(itemView).navigate(MainFragmentDirections.actionMainFragmentToAddFragment(todoItem))
        }
    )

    fun updateTodoList() {
        viewModelScope.launch {
            val list = repository.fetchTodoList()
            Log.i("deb", list.toString())
            if (showDoneFlag)
                todoListAdapter.submitList(list)
            else
                todoListAdapter.submitList(list.filter { it.done })
        }
    }

}
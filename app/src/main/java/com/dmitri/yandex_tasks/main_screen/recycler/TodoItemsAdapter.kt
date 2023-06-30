package com.dmitri.yandex_tasks.main_screen.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.util.entity.TodoItem

class TodoItemsAdapter(
    private val onCheckboxSwitch: (todoItem: TodoItem) -> Unit,
    private val onItemClick: (todoItem: TodoItem, itemView: View) -> Unit
): ListAdapter<TodoItem, TodoItemViewHolder>(DiffUtilCallbackImpl()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            R.layout.todo_item,
            parent,
            false
        )
        return TodoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.onBind(getItem(position), onCheckboxSwitch, onItemClick)
    }
}
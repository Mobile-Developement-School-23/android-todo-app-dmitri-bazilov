package com.dmitri.yandex_tasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.TaskApplication
import com.dmitri.yandex_tasks.util.entity.TodoItem
import com.dmitri.yandex_tasks.view_holder.TodoItemViewHolder

class TodoItemsAdapter(): RecyclerView.Adapter<TodoItemViewHolder>() {

    var todoItemsList = listOf<TodoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TodoItemViewHolder(
            layoutInflater.inflate(
                R.layout.todo_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = todoItemsList.size

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.onBind(todoItemsList[position])
    }
}
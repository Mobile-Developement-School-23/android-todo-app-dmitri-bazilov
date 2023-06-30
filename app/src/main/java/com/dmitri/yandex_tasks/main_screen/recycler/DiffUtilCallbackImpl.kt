package com.dmitri.yandex_tasks.main_screen.recycler

import androidx.recyclerview.widget.DiffUtil
import com.dmitri.yandex_tasks.util.entity.TodoItem

class DiffUtilCallbackImpl: DiffUtil.ItemCallback<TodoItem>() {

    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
        oldItem == newItem
}
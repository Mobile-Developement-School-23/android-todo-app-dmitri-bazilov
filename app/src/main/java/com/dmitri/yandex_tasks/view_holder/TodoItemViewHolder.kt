package com.dmitri.yandex_tasks.view_holder

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.util.entity.TodoItem

class TodoItemViewHolder(todoItemView: View): ViewHolder(todoItemView) {

    private val description: TextView = todoItemView.findViewById(R.id.description)
    private val checkbox: CheckBox = todoItemView.findViewById(R.id.completeCheckbox)

    init {
        checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                description.paintFlags = description.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else
                description.paintFlags = description.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun onBind(todoItem: TodoItem) {
        checkbox.isChecked = todoItem.done
        description.text = todoItem.description
    }
}
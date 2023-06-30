package com.dmitri.yandex_tasks.main_screen.recycler

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.util.entity.Priority
import com.dmitri.yandex_tasks.util.entity.TodoItem

class TodoItemViewHolder(private val todoItemView: View) : ViewHolder(todoItemView) {

    private val description: TextView = todoItemView.findViewById(R.id.description)
    private val checkbox: CheckBox = todoItemView.findViewById(R.id.completeCheckbox)
    private val priority: ImageView = todoItemView.findViewById(R.id.todoPriority)

    fun onBind(
        todoItem: TodoItem,
        onCheckboxSwitch: (todoItem: TodoItem) -> Unit,
        onItemClick: (todoItem: TodoItem, view: View) -> Unit
    ) {

        todoItemView.setOnClickListener {
            onItemClick(todoItem, todoItemView)
        }
        checkbox.setOnCheckedChangeListener { _, _ ->
            onCheckboxSwitch(todoItem)
        }

        checkbox.isChecked = todoItem.done
        description.text = todoItem.description
        if (todoItem.done) {
            description.paintFlags = description.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            description.paintFlags = description.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        when (todoItem.priority) {
            Priority.LOW -> {
                priority.visibility = View.VISIBLE
                priority.setImageResource(R.drawable.down_arrow)
            }
            Priority.MEDIUM -> {
                priority.visibility = View.INVISIBLE
                priority.setImageDrawable(null)
            }
            Priority.HIGH -> {
                priority.visibility = View.VISIBLE
                priority.setImageResource(R.drawable.up_arrow)
            }
        }

    }
}
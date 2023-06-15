package com.dmitri.yandex_tasks.util.repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.util.entity.Priority
import com.dmitri.yandex_tasks.util.entity.TodoItem
import java.time.LocalDate

class TodoItemsRepository {

    fun getItems(context: Context): List<TodoItem> {
        return buildList {
            val done = (0..1).random()
            val checkbox = AppCompatResources.getDrawable(
                context,
                if (done == 0) R.drawable.unchecked_24
                else R.drawable.checked_24
            )
            val description = "Test Todo"

            val numberOfItems = (1..10).random()
            for (i in 0..numberOfItems)
                add(TodoItem(i.toString(), checkbox!!, description, Priority.HIGH, null, done == 1, LocalDate.now(), null))
        }
    }
}
package com.dmitri.yandex_tasks.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dmitri.yandex_tasks.database.AppDatabase
import com.dmitri.yandex_tasks.database.entity.TodoEntity
import com.dmitri.yandex_tasks.util.entity.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("select * from ${TodoEntity.TABLE_NAME}")
    suspend fun getAllTodos() : List<TodoEntity>

    @Insert(entity = TodoEntity::class)
    suspend fun insertTodoItem(todoEntity: TodoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItems(items: List<TodoEntity>)

    @Update(entity = TodoEntity::class)
    suspend fun updateTodoItem(todoEntity: TodoEntity)

    @Delete(entity = TodoEntity::class)
    suspend fun deleteTodoItem(todoEntity: TodoEntity)
}
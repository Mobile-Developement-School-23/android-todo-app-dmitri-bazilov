package com.dmitri.yandex_tasks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dmitri.yandex_tasks.database.dao.TodoDao
import com.dmitri.yandex_tasks.database.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}
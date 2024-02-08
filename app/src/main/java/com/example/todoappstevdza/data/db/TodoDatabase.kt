package com.example.todoappstevdza.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoappstevdza.data.dao.TodoDAO
import com.example.todoappstevdza.data.entity.Todo


@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false,
)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDAO
}
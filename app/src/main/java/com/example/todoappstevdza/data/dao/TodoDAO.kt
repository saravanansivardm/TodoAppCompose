package com.example.todoappstevdza.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoappstevdza.data.entity.Todo
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDAO {

    @Query("SELECT * from todos_tbl")
    fun getAllTodos(): Flow<List<Todo>>

    @Query("SELECT * from todos_tbl where id =:id")
    suspend fun getTodoById(id: String): Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(todo: Todo)

    @Query("DELETE from todos_tbl")
    suspend fun deleteAllTodos()

    @Delete
    suspend fun deleteTodo(todo: Todo)
}
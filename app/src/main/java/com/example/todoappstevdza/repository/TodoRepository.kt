package com.example.todoappstevdza.repository

import com.example.todoappstevdza.data.dao.TodoDAO
import com.example.todoappstevdza.data.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDAO) {
    suspend fun addTodo(todo: Todo) = todoDao.insert(todo)
    suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)
    suspend fun updateTodo(todo: Todo) = todoDao.update(todo)
    suspend fun getAllTodo(): Flow<List<Todo>> = todoDao.getAllTodos().flowOn(Dispatchers.IO).conflate()
    suspend fun getTodoById(id: String) = todoDao.getTodoById(id)
    suspend fun deleteAllTodo(todo: List<Todo>) = todoDao.deleteAllTodos()
}
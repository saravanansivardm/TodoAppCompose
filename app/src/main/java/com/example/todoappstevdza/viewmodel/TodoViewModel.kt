package com.example.todoappstevdza.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappstevdza.data.entity.Todo
import com.example.todoappstevdza.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())
    val todoList = _todoList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTodo().distinctUntilChanged().collect() { listOfTodos ->
                if (listOfTodos.isEmpty()) {
                    Log.d("Empty", ": Empty list")
                } else {
                    _todoList.value = listOfTodos
                }
            }
        }
    }

    fun addTodo(todo: Todo) = viewModelScope.launch { repository.addTodo(todo) }
    fun updateTodo(todo: Todo) = viewModelScope.launch { repository.updateTodo(todo) }
    fun removeTodo(todo: Todo) = viewModelScope.launch { repository.deleteTodo(todo) }
    fun deleteAllTodo(todo: List<Todo>) = viewModelScope.launch { repository.deleteAllTodo(todo) }
}
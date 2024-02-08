package com.example.todoappstevdza

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.todoappstevdza.navigation.TodoNavigationHost
import com.example.todoappstevdza.ui.theme.TodoAppStevdzaTheme
import com.example.todoappstevdza.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val todosViewModel: TodoViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppStevdzaTheme {
                val navController = rememberNavController()
                TodoNavigationHost(navController,todosViewModel)
            }
        }
    }
}
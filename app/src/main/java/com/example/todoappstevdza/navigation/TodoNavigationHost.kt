package com.example.todoappstevdza.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoappstevdza.screens.HomeScreen
import com.example.todoappstevdza.screens.InputScreen
import com.example.todoappstevdza.viewmodel.TodoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoNavigationHost(
    navHostController: NavHostController,
    todosViewModel: TodoViewModel,
) {
    val todosList = todosViewModel.todoList.collectAsState().value

    NavHost(
        navController = navHostController, startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route, content = {
            HomeScreen(
                todosList = todosList,
                navHostController = navHostController,
                onRemoveNote = {
                    todosViewModel.removeTodo(it)
                },
                onRemoveAllNotes = {
                    todosViewModel.deleteAllTodo(it)
                }
            )
        })

        composable(
            route = Screen.InputScreen.route,
            arguments = listOf(
                navArgument(DETAILS_ARGUMENT_ID_KEY) {
                    type = NavType.IntType
                },
                navArgument(DETAILS_ARGUMENT_NAME_KEY) {
                    type = NavType.StringType
                },
                navArgument(DETAILS_ARGUMENT_DESCRIPTION_KEY) {
                    type = NavType.StringType
                },
                navArgument(DETAILS_ARGUMENT_ENABLED_KEY) {
                    type = NavType.BoolType
                },
            )
        ) {
            val id = it.arguments?.getInt(DETAILS_ARGUMENT_ID_KEY).toString()
            val name = it.arguments?.getString(DETAILS_ARGUMENT_NAME_KEY).toString()
            val description = it.arguments?.getString(DETAILS_ARGUMENT_DESCRIPTION_KEY).toString()
            val enabled = it.arguments?.getBoolean(DETAILS_ARGUMENT_ENABLED_KEY)

            InputScreen(
                navHostController = navHostController,
                onAddTodo = {
                    todosViewModel.addTodo(it)
                },
                onUpdateTodo = {
                    todosViewModel.updateTodo(it)
                },
                id = id,
                name = name,
                description = description,
                enabled = enabled == true
            )
        }
    }
}
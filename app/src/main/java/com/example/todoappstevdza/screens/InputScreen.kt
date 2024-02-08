package com.example.todoappstevdza.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todoappstevdza.components.TodosButton
import com.example.todoappstevdza.components.TodosInputText
import com.example.todoappstevdza.data.entity.Todo
import com.example.todoappstevdza.navigation.Screen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    navHostController: NavHostController,
    onAddTodo: (Todo) -> Unit,
    onUpdateTodo: (Todo) -> Unit,
    id: String = "",
    name: String,
    description: String = "",
    enabled: Boolean,
) {
    val titleInput = remember {
        mutableStateOf("")
    }
    val descriptionInput = remember {
        mutableStateOf("")
    }
    val titleInputUpdate = remember {
        mutableStateOf(name)
    }
    val descriptionInputUpdate = remember {
        mutableStateOf(description)
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (enabled) {
                TodosInputText(
                    onTextChange = {
                        titleInputUpdate.value = it
                    },
                    text = titleInputUpdate.value,
                    label = "Title",
                    isSingleLine = true,
                    maxLine = 1,
                )
                TodosInputText(
                    onTextChange = {
                        descriptionInputUpdate.value = it
                    },
                    text = descriptionInputUpdate.value,
                    label = "Description",
                    isSingleLine = true,
                    maxLine = 1
                )

                TodosButton(text = "Update", isEnabled = true, onClicked = {
                    if (titleInputUpdate.value.isNotEmpty() && descriptionInputUpdate.value.isNotEmpty()) {
                        onUpdateTodo(
                            Todo(
                                id = id.toInt(),
                                title = titleInputUpdate.value,
                                description = descriptionInputUpdate.value,
                            )
                        )
                        titleInputUpdate.value = ""
                        descriptionInputUpdate.value = ""
                        Toast.makeText(context, "Todo Updated Successfully", Toast.LENGTH_SHORT)
                            .show()
                        navHostController.navigate(Screen.HomeScreen.route)
                    }
                })
            } else {
                TodosInputText(
                    onTextChange = {
                        titleInput.value = it
                    },
                    text = titleInput.value,
                    label = "Title",
                    isSingleLine = true,
                    maxLine = 1,
                )
                TodosInputText(
                    onTextChange = {
                        descriptionInput.value = it
                    },
                    text = descriptionInput.value,
                    label = "Description",
                    isSingleLine = true,
                    maxLine = 1
                )

                TodosButton(text = "Submit", isEnabled = true, onClicked = {
                    if (titleInput.value.isNotEmpty() && descriptionInput.value.isNotEmpty()) {
                        onAddTodo(
                            Todo(
                                title = titleInput.value,
                                description = descriptionInput.value,
                            )
                        )
                        titleInput.value = ""
                        descriptionInput.value = ""
                        Toast.makeText(context, "Todo Added Successfully", Toast.LENGTH_SHORT)
                            .show()
                        navHostController.navigate(Screen.HomeScreen.route)
                    }
                })
            }
        }
    }

}
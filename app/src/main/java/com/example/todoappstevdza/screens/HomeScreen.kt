package com.example.todoappstevdza.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoappstevdza.R
import com.example.todoappstevdza.data.entity.Todo
import com.example.todoappstevdza.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    todosList: List<Todo>,
    navHostController: NavHostController,
    onRemoveNote: (Todo) -> Unit,
    onRemoveAllNotes: (List<Todo>) -> Unit,
) {
    val id = 0
    Scaffold(Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }, actions = {
                    if (todosList.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Icon",
                            modifier = Modifier.clickable {
                                onRemoveAllNotes(todosList)
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape, onClick = {
                    navHostController.navigate(
                        route = Screen.InputScreen.passInputDetails(
                            id,
                            "test",
                            "test",
                            false
                        )
                    )
                }, contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
            }
        }) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (todosList.isEmpty()) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        textAlign = TextAlign.Center,
                        text = "Todo list is empty",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                    )
                }
            } else {
                LazyColumn(
                    Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
                ) {
                    itemsIndexed(todosList) { index, todos ->
                        TodoRow(
                            todo = todos,
                            navHostController = navHostController,
                            onNoteClicked = {
                                onRemoveNote(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TodoRow(
    todo: Todo,
    navHostController: NavHostController,
    onNoteClicked: (Todo) -> Unit,
) {
    val id = todo.id
    val title = todo.title
    val desc = todo.description
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(0.1f)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                .clickable {
                    navHostController.navigate(
                        route = Screen.InputScreen.passInputDetails(
                            todo.id,
                            todo.title,
                            todo.description,
                            todo.enabled
                        )
                    )
                }, horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp),
                    text = todo.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                )
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 8.dp)
                        .clickable {
                            onNoteClicked(todo)
                            Toast
                                .makeText(context, "Todo Deleted Successfully", Toast.LENGTH_SHORT)
                                .show()
                        },
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Icon"
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = todo.description,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            )
        }
    }
}
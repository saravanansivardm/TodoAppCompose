package com.example.todoappstevdza.navigation

const val DETAILS_ARGUMENT_ID_KEY = "id"
const val DETAILS_ARGUMENT_NAME_KEY = "name"
const val DETAILS_ARGUMENT_DESCRIPTION_KEY = "description"
const val DETAILS_ARGUMENT_ENABLED_KEY = "enabled"

sealed class Screen(
    val route: String,
) {
    object HomeScreen : Screen("home_screen")
    object InputScreen :
        Screen(
            route = "input_screen/{$DETAILS_ARGUMENT_ID_KEY}/" +
                    "{$DETAILS_ARGUMENT_NAME_KEY}/" +
                    "{$DETAILS_ARGUMENT_DESCRIPTION_KEY}/" +
                    "{$DETAILS_ARGUMENT_ENABLED_KEY}"
        ) {
        fun passInputDetails(
            id: Int,
            name: String,
            description: String,
            enabled: Boolean,
        ): String {
            return "input_screen/$id/$name/$description/$enabled"
        }
    }
}


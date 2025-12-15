package com.webpa.mobile.presentation.navigation

sealed class Screens(val route: String) {
    object Search: Screens("search_screen")
    object Products: Screens("products_screen")
    object Profile: Screens("profile_screen")
}

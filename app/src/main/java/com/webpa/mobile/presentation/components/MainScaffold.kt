package com.webpa.mobile.presentation.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.webpa.mobile.presentation.navigation.BottomBar
import com.webpa.mobile.presentation.navigation.Screens

@Composable
fun MainScaffold(
    navController: NavHostController,
    content: @Composable (Modifier) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route

    val showBottomBar = route in listOf(
        Screens.Search.route,
        Screens.Favorites.route,
        Screens.Profile.route
    )

    Scaffold(
        topBar = {
            AppTopBar(title = "Webpa")
        },
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController)
            }
        }
    ) { padding ->
        content(Modifier.padding(padding))
    }
}

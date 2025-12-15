package com.webpa.mobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.webpa.mobile.presentation.products.ProductsScreen
import com.webpa.mobile.presentation.profile.ProfileScreen
import com.webpa.mobile.presentation.search.SearchScreen

@Composable
fun NavGraph (navController: NavHostController, modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = Screens.Search.route)
    {
        composable(route = Screens.Search.route){
            SearchScreen()
        }
        composable(route = Screens.Products.route){
            ProductsScreen()
        }
        composable(route = Screens.Profile.route){
            ProfileScreen()
        }
    }
}



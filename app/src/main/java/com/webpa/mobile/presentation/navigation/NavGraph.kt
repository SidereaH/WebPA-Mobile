package com.webpa.mobile.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.webpa.mobile.presentation.auth.LoginScreen
import com.webpa.mobile.presentation.auth.RegisterScreen
import com.webpa.mobile.presentation.components.MainScaffold
import com.webpa.mobile.presentation.favorites.FavoritesScreen
import com.webpa.mobile.presentation.products.details.ProductDetailsScreen
import com.webpa.mobile.presentation.profile.ProfileScreen
import com.webpa.mobile.presentation.search.SearchScreen
@Composable
fun RootNavGraph(
    navController: NavHostController,
    isAuthorized: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isAuthorized) "main" else "auth"
    ) {
        authNavGraph(navController)
        mainNavGraph(navController)
    }
}


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = "auth",
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onSuccess = {
                    navController.navigate("main") {
                        popUpTo("auth") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onSuccess = {
                    navController.navigate("main") {
                        popUpTo("auth") { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {
    navigation(
        route = "main",
        startDestination = Screens.Search.route
    ) {

        composable(Screens.Search.route) {
            MainScaffold(navController) { padding ->
                SearchScreen(
                    onProductClick = { id ->
                        navController.navigate(
                            Screens.ProductDetails.createRoute(id)
                        )
                    },
                    modifier = Modifier.padding(padding)
                )
            }
        }

        composable(Screens.Favorites.route) {
            MainScaffold(navController) { padding ->
                FavoritesScreen(
                    onProductClick = { id ->
                        navController.navigate(
                            Screens.ProductDetails.createRoute(id)
                        )
                    },
                    modifier = Modifier.padding(padding)
                )
            }
        }

        composable(Screens.Profile.route) {
            MainScaffold(navController) { padding ->
                ProfileScreen(
                    onLogout = {
                        navController.navigate("auth") {
                            popUpTo("main") { inclusive = true }
                        }
                    },
                    modifier = Modifier.padding(padding)
                )
            }

        }

        composable(
            route = Screens.ProductDetails.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.LongType }
            )
        ) {
            ProductDetailsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}


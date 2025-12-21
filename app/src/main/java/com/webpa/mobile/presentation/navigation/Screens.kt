package com.webpa.mobile.presentation.navigation

sealed class Screens(val route: String) {
    object Search: Screens("search_screen")
    object Products: Screens("products_screen")
    object Profile: Screens("profile_screen")
    object ProductDetails : Screens("product_details/{productId}"){
        fun createRoute(productId: Long) =
            "product_details/$productId"
    }
    object Favorites : Screens("favorites_screen")

}

package com.webpa.mobile.presentation.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)
val bottomItems = listOf(
    BottomNavItem(Screens.Search.route, "Поиск", Icons.Default.Search),
    BottomNavItem(Screens.Favorites.route, "Избранное", Icons.Default.Star),
    BottomNavItem(Screens.Profile.route, "Профиль", Icons.Default.Person)
)
@Composable
fun BottomBar(
    navController: NavController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        bottomItems.forEach { item ->
            val selected = currentRoute == item.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        popUpTo("main") { saveState = true }
                        restoreState = true
                    }
                },
                icon = {
                    AnimatedScaleIcon(
                        selected = selected,
                        icon = item.icon
                    )
                },
                label = { Text(item.label) }
            )
        }
    }
}
@Composable
fun AnimatedScaleIcon(
    selected: Boolean,
    icon: ImageVector
) {
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.2f else 1f,
        label = "scale"
    )

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.scale(scale)
    )
}

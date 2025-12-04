package com.example.taskmanager.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskmanager.Constants

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object CreateTask : BottomNavItem(
        Constants.CREATE_NAV_TITLE,
        Icons.Default.AddCircle,
        "create_task"
    )
    object ListTask : BottomNavItem(
        Constants.LIST_NAV_TITLE,
        Icons.Default.DateRange,
        "list_task"
    )
    object Settings : BottomNavItem(
        Constants.CONFIGURATION_NAV_TITLE,
        Icons.Default.Settings,
        "settings"
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.CreateTask,
        BottomNavItem.ListTask,
        BottomNavItem.Settings
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
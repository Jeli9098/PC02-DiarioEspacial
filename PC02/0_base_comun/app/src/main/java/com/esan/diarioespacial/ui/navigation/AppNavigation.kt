package com.esan.diarioespacial.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.esan.diarioespacial.ui.apod.apodGraph
import com.esan.diarioespacial.ui.asteroids.asteroidGraph

private data class BottomItem(val route: String, val label: String, val icon: ImageVector)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val items = listOf(
        BottomItem(Routes.IMAGENES, "Imágenes", Icons.Filled.Image),
        BottomItem(Routes.ASTEROIDES, "Asteroides", Icons.Filled.Public)
    )

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            NavigationBar {
                val backStack by navController.currentBackStackEntryAsState()
                val current = backStack?.destination
                items.forEach { item ->
                    NavigationBarItem(
                        selected = current?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.IMAGENES,
            modifier = Modifier.padding(padding).consumeWindowInsets(padding)
        ) {
            apodGraph(navController)      // Alumno 1 -> ui/apod/ApodNavigation.kt
            asteroidGraph(navController)  // Alumno 2 -> ui/asteroids/AsteroidNavigation.kt
        }
    }
}

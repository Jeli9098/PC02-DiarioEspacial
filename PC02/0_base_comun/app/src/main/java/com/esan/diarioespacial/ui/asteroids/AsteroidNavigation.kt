package com.esan.diarioespacial.ui.asteroids

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.esan.diarioespacial.ui.navigation.Routes

// STUB de la base común. El Alumno 2 lo reemplaza con su navegación real.
fun NavGraphBuilder.asteroidGraph(navController: NavHostController) {
    navigation(startDestination = Routes.NEO_LIST, route = Routes.ASTEROIDES) {
        composable(Routes.NEO_LIST) { Text("Asteroides – en construcción") }
    }
}

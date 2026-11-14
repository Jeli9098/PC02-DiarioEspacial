package com.esan.diarioespacial.ui.apod

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.esan.diarioespacial.ui.navigation.Routes

// STUB de la base común. El Alumno 1 lo reemplaza con su navegación real.
fun NavGraphBuilder.apodGraph(navController: NavHostController) {
    navigation(startDestination = Routes.APOD_LIST, route = Routes.IMAGENES) {
        composable(Routes.APOD_LIST) { Text("Imágenes – en construcción") }
    }
}

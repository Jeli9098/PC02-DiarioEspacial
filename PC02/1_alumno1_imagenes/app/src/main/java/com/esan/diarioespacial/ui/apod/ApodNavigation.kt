package com.esan.diarioespacial.ui.apod

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.esan.diarioespacial.ui.navigation.Routes

/** Navegación interna de la opción Imágenes (reemplaza el stub de la base común). */
fun NavGraphBuilder.apodGraph(navController: NavHostController) {
    navigation(startDestination = Routes.APOD_LIST, route = Routes.IMAGENES) {

        composable(Routes.APOD_LIST) { entry ->
            ApodListScreen(
                viewModel = entry.apodViewModel(navController),
                onOpenDetail = { date -> navController.navigate(Routes.apodDetail(date)) },
                onOpenFavorites = { navController.navigate(Routes.APOD_FAVORITES) }
            )
        }

        composable(Routes.APOD_DETAIL) { entry ->
            ApodDetailScreen(
                viewModel = entry.apodViewModel(navController),
                date = entry.arguments?.getString("date").orEmpty(),
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.APOD_FAVORITES) { entry ->
            ApodFavoritesScreen(
                viewModel = entry.apodViewModel(navController),
                onOpenDetail = { date -> navController.navigate(Routes.apodDetail(date)) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}

/** Un solo ViewModel compartido por las 3 pantallas (vive mientras viva el grafo "imagenes"). */
@Composable
private fun NavBackStackEntry.apodViewModel(navController: NavHostController): ApodViewModel {
    val parent = remember(this) { navController.getBackStackEntry(Routes.IMAGENES) }
    return viewModel(viewModelStoreOwner = parent, factory = ApodViewModel.Factory)
}

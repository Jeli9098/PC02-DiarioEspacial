package com.esan.diarioespacial.ui.apod

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.esan.diarioespacial.ui.components.EmptyView
import com.esan.diarioespacial.ui.components.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApodListScreen(
    viewModel: ApodViewModel,
    onOpenDetail: (String) -> Unit,
    onOpenFavorites: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Imágenes") },
                actions = {
                    IconButton(onClick = onOpenFavorites) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Mis imágenes favoritas")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).fillMaxSize()) {
            // Indicador de carga mientras se consulta la API (aunque haya datos guardados)
            if (state.isLoading && state.items.isNotEmpty()) {
                LinearProgressIndicator(Modifier.fillMaxWidth())
            }
            when {
                state.isLoading && state.items.isEmpty() -> LoadingView()
                state.items.isEmpty() -> EmptyView(
                    message = state.error ?: "No hay imágenes para mostrar.",
                    onRetry = viewModel::refresh
                )
                else -> LazyColumn(Modifier.fillMaxSize()) {
                    items(state.items, key = { it.date }) { item ->
                        ApodItem(
                            item = item,
                            onClick = { onOpenDetail(item.date) },
                            onToggleFavorite = { viewModel.toggleFavorite(item) }
                        )
                    }
                }
            }
        }
    }
}

package com.esan.diarioespacial.ui.apod

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.esan.diarioespacial.ui.components.EmptyView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApodFavoritesScreen(
    viewModel: ApodViewModel,
    onOpenDetail: (String) -> Unit,
    onBack: () -> Unit
) {
    val favorites by viewModel.favorites.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis imágenes favoritas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        if (favorites.isEmpty()) {
            EmptyView("Aún no tienes imágenes favoritas.", Modifier.padding(padding))
        } else {
            LazyColumn(Modifier.padding(padding).fillMaxSize()) {
                items(favorites, key = { it.date }) { item ->
                    ApodItem(
                        item = item,
                        onClick = { onOpenDetail(item.date) },
                        onToggleFavorite = { viewModel.toggleFavorite(item) } // quita de favoritos
                    )
                }
            }
        }
    }
}

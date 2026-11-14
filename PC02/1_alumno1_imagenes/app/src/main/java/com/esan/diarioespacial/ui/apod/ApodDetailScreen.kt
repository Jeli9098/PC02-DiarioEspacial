package com.esan.diarioespacial.ui.apod

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.esan.diarioespacial.ui.components.EmptyView
import com.esan.diarioespacial.ui.components.FavoriteButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApodDetailScreen(viewModel: ApodViewModel, date: String, onBack: () -> Unit) {
    // Lee desde Room: si cambia el favorito, la pantalla se actualiza sola
    val item by remember(date) { viewModel.observeByDate(date) }.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    item?.let { apod ->
                        FavoriteButton(isFavorite = apod.isFavorite) { viewModel.toggleFavorite(apod) }
                    }
                }
            )
        }
    ) { padding ->
        val apod = item
        if (apod == null) {
            EmptyView("No se encontró la imagen.", Modifier.padding(padding))
        } else {
            Column(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                if (apod.mediaType == "video") {
                    VideoPlaceholder(height = 180.dp)
                } else {
                    AsyncImage(
                        model = apod.hdUrl ?: apod.url,   // hdurl si existe, si no url
                        contentDescription = apod.title,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(apod.title, style = MaterialTheme.typography.headlineSmall)
                Text(apod.date, style = MaterialTheme.typography.bodyMedium)
                apod.copyright?.let {
                    Text("Autor: $it", style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(Modifier.height(12.dp))
                Text(apod.explanation, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

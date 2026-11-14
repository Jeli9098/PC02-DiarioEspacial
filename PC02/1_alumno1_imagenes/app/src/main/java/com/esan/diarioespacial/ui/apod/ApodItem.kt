package com.esan.diarioespacial.ui.apod

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.esan.diarioespacial.data.local.ApodEntity
import com.esan.diarioespacial.ui.components.FavoriteButton

/** Elemento de lista: lo usan la lista principal y "Mis imágenes favoritas". */
@Composable
fun ApodItem(item: ApodEntity, onClick: () -> Unit, onToggleFavorite: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            if (item.mediaType == "video") {
                VideoPlaceholder(height = 90.dp)
            } else {
                AsyncImage(
                    model = item.url,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
            }
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(item.title, style = MaterialTheme.typography.titleMedium)
                    Text(item.date, style = MaterialTheme.typography.bodySmall)
                }
                FavoriteButton(isFavorite = item.isFavorite, onClick = onToggleFavorite)
            }
        }
    }
}

/** Para media_type = "video": no se carga imagen. */
@Composable
fun VideoPlaceholder(height: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text("Contenido en video", style = MaterialTheme.typography.titleSmall)
    }
}

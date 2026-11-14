package com.esan.diarioespacial.data.mapper

import com.esan.diarioespacial.data.local.ApodEntity
import com.esan.diarioespacial.data.remote.apod.ApodDto

/** DTO -> Entity. Si falta la fecha (clave primaria) el elemento se descarta. */
fun ApodDto.toEntityOrNull(): ApodEntity? {
    val safeDate = date ?: return null
    return ApodEntity(
        date = safeDate,
        title = title ?: "Sin título",
        explanation = explanation ?: "Sin descripción.",
        url = url,
        hdUrl = hdurl,
        mediaType = mediaType ?: "image",
        copyright = copyright?.replace("\n", " ")?.trim()
    )
}

fun List<ApodDto>.toEntities(): List<ApodEntity> = mapNotNull { it.toEntityOrNull() }

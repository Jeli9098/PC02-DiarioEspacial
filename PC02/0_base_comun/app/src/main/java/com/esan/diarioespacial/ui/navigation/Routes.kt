package com.esan.diarioespacial.ui.navigation

object Routes {
    // Grafos de la barra inferior
    const val IMAGENES = "imagenes"
    const val ASTEROIDES = "asteroides"

    // Alumno 1
    const val APOD_LIST = "apod_list"
    const val APOD_DETAIL = "apod_detail/{date}"
    const val APOD_FAVORITES = "apod_favorites"
    fun apodDetail(date: String) = "apod_detail/$date"

    // Alumno 2
    const val NEO_LIST = "neo_list"
    const val NEO_DETAIL = "neo_detail/{id}"
    const val NEO_FAVORITES = "neo_favorites"
    fun neoDetail(id: String) = "neo_detail/$id"
}

package com.esan.diarioespacial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// STUB de la base común. El Alumno 1 lo reemplaza con la versión completa.
@Entity(tableName = "apod")
data class ApodEntity(
    @PrimaryKey val date: String
)

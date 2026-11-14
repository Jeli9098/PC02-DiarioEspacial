package com.esan.diarioespacial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// STUB de la base común. El Alumno 2 lo reemplaza con la versión completa.
@Entity(tableName = "asteroids")
data class AsteroidEntity(
    @PrimaryKey val id: String
)

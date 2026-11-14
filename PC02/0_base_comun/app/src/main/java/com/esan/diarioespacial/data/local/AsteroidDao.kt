package com.esan.diarioespacial.data.local

import androidx.room.Dao
import androidx.room.Query

// STUB de la base común. El Alumno 2 lo reemplaza con la versión completa.
@Dao
interface AsteroidDao {
    @Query("SELECT COUNT(*) FROM asteroids")
    suspend fun count(): Int
}

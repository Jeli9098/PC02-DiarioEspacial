package com.esan.diarioespacial.data.local

import androidx.room.Dao
import androidx.room.Query

// STUB de la base común. El Alumno 1 lo reemplaza con la versión completa.
@Dao
interface ApodDao {
    @Query("SELECT COUNT(*) FROM apod")
    suspend fun count(): Int
}

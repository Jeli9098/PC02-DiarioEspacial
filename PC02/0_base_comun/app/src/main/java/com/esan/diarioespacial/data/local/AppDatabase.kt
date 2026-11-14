package com.esan.diarioespacial.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Base de datos compartida. Ya registra las dos tablas y los dos DAO,
 * así ningún alumno tiene que tocar este archivo (evita conflictos).
 * Cada alumno solo completa su Entity y su DAO.
 */
@Database(
    entities = [ApodEntity::class, AsteroidEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun apodDao(): ApodDao
    abstract fun asteroidDao(): AsteroidDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "diario_espacial.db"
                )
                    // Si cambian columnas durante el desarrollo, la BD se recrea
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
    }
}

package com.esan.diarioespacial.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ApodDao {

    @Query("SELECT * FROM apod WHERE date BETWEEN :start AND :end ORDER BY date DESC")
    fun observeRange(start: String, end: String): Flow<List<ApodEntity>>

    @Query("SELECT * FROM apod WHERE isFavorite = 1 ORDER BY date DESC")
    fun observeFavorites(): Flow<List<ApodEntity>>

    @Query("SELECT * FROM apod WHERE date = :date")
    fun observeByDate(date: String): Flow<ApodEntity?>

    @Query("SELECT date FROM apod WHERE isFavorite = 1")
    suspend fun favoriteDates(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ApodEntity>)

    @Query("UPDATE apod SET isFavorite = :favorite WHERE date = :date")
    suspend fun setFavorite(date: String, favorite: Boolean)

    /** Guarda lo que llegó de la API SIN perder los favoritos ya marcados. */
    @Transaction
    suspend fun upsertKeepingFavorites(items: List<ApodEntity>) {
        val favs = favoriteDates().toSet()
        insertAll(items.map { it.copy(isFavorite = it.date in favs) })
    }
}

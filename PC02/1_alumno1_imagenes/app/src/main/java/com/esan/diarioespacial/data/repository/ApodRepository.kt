package com.esan.diarioespacial.data.repository

import com.esan.diarioespacial.data.local.ApodDao
import com.esan.diarioespacial.data.local.ApodEntity
import com.esan.diarioespacial.data.mapper.toEntities
import com.esan.diarioespacial.data.remote.apod.ApodApi
import com.esan.diarioespacial.util.DateUtils
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class ApodRepository(
    private val api: ApodApi,
    private val dao: ApodDao
) {
    fun observeRange(start: String, end: String): Flow<List<ApodEntity>> = dao.observeRange(start, end)
    fun observeFavorites(): Flow<List<ApodEntity>> = dao.observeFavorites()
    fun observeByDate(date: String): Flow<ApodEntity?> = dao.observeByDate(date)

    /** Consulta la API y guarda en Room. La UI se actualiza sola por el Flow. */
    suspend fun refresh(start: String, end: String) {
        val dtos = try {
            api.getApodRange(start, end)
        } catch (e: HttpException) {
            // La NASA publica la imagen del día según hora de EE.UU.;
            // si "hoy" aún no existe responde 400 -> reintentamos hasta ayer.
            if (e.code() == 400) api.getApodRange(start, DateUtils.daysAgo(1)) else throw e
        }
        dao.upsertKeepingFavorites(dtos.toEntities())
    }

    suspend fun toggleFavorite(item: ApodEntity) = dao.setFavorite(item.date, !item.isFavorite)
}

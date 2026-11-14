package com.esan.diarioespacial.data.remote.apod

import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {
    // api_key la agrega RetrofitClient (interceptor)
    @GET("planetary/apod")
    suspend fun getApodRange(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): List<ApodDto>
}

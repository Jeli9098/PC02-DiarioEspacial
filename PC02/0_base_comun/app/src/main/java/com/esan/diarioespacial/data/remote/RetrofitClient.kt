package com.esan.diarioespacial.data.remote

import com.esan.diarioespacial.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit compartido. La api_key se agrega a TODAS las peticiones con un
 * interceptor, así ningún endpoint la repite y nunca queda escrita en el código.
 * Cada alumno crea su propia interfaz: RetrofitClient.retrofit.create(XxxApi::class.java)
 */
object RetrofitClient {
    private const val BASE_URL = "https://api.nasa.gov/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.NASA_API_KEY)
                .build()
            chain.proceed(original.newBuilder().url(url).build())
        }
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

package com.example.m_search.network

import com.example.m_search.data.Medicine
import com.example.m_search.data.MedicineSer
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://luisbittau.pythonanywhere.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface MSearchApiService {
    @GET("m_search")
    suspend fun getMedicines(@Query("input") input: String): List<MedicineSer>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MSearchApi {
    val retrofitService: MSearchApiService by lazy {
        retrofit.create(MSearchApiService::class.java)
    }
}
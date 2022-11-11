package com.androidandrew.fleetio_assessment.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://secure.fleetio.com"

val json = Json { ignoreUnknownKeys = true }

@ExperimentalSerializationApi
var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

interface IFleetioVehiclesService {
    @Headers(
        // Removed for commit. Move to gradle.properties later.
    )
    @GET("api/v1/vehicles")
    suspend fun getVehicles(): List<FleetioVehicle>
}

@ExperimentalSerializationApi
val fleetioVehiclesService: IFleetioVehiclesService = retrofit.create(IFleetioVehiclesService::class.java)
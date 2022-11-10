package com.androidandrew.fleetio_assessment.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://secure.fleetio.com"

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface FleetioVehiclesService {
    @Headers(
        // Removed for commit. Move to gradle.properties later.
    )
    @GET("api/v1/vehicles")
    suspend fun getVehicles(): String
}

val fleetioVehiclesService: FleetioVehiclesService = retrofit.create(FleetioVehiclesService::class.java)
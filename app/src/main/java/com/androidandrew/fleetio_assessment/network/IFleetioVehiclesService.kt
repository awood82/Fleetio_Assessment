package com.androidandrew.fleetio_assessment.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

//private const val BASE_URL = "https://secure.fleetio.com"

//val json = Json { ignoreUnknownKeys = true }
//
//@ExperimentalSerializationApi
//var retrofit: Retrofit = Retrofit.Builder()
//    .baseUrl(BASE_URL)
////    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//    .build()

interface IFleetioVehiclesService {
    companion object {
        val json = Json { ignoreUnknownKeys = true }
        const val BASE_URL = "https://secure.fleetio.com"
        const val FIRST_PAGE = 1
        const val VEHICLES_PER_PAGE = 50 // TODO: This can change at any time. Does setting it in the header change it in the response?
    }

    @Headers(
        // Removed for commit. Move to gradle.properties later.
        "Authorization: Token token=a3ddc620b35b609682192c167de1b1f3f5100387",
        "Account-Token: 798819214b"
    )
    @GET("api/v1/vehicles")
    suspend fun getVehicles(
        @Query("page") page: Int = FIRST_PAGE
    ): List<FleetioVehicle>

    @Headers(
        // Removed for commit. Move to gradle.properties later.
        "Authorization: Token token=a3ddc620b35b609682192c167de1b1f3f5100387",
        "Account-Token: 798819214b"
    )
    @GET("api/v1/vehicles/{id}")
    suspend fun getVehicleDetails(@Path("id") id: String): FleetioVehicleDetails
}

//@ExperimentalSerializationApi
//val fleetioVehiclesService: IFleetioVehiclesService = retrofit.create(IFleetioVehiclesService::class.java)
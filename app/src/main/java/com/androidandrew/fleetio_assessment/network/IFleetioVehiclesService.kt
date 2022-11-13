package com.androidandrew.fleetio_assessment.network

import kotlinx.serialization.json.Json
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IFleetioVehiclesService {
    companion object {
        val json = Json { ignoreUnknownKeys = true }
        const val BASE_URL = "https://secure.fleetio.com"
        const val FIRST_PAGE = 1
        const val VEHICLES_PER_PAGE = 50 // For the paging library, not for the # of vehicles/page configured on the server
    }

    @Headers(
        "Authorization: Token token=a3ddc620b35b609682192c167de1b1f3f5100387",
        "Account-Token: 798819214b"
    )
    @GET("api/v1/vehicles")
    suspend fun getVehicles(
        @Query("q[make_cont]") make: String? = null,
        @Query("page") page: Int = FIRST_PAGE
    ): List<FleetioVehicle>

//    @Headers(
//        // Removed for commit. Move to gradle.properties later.
//    )
//    @GET("api/v1/vehicles")
//    suspend fun getVehiclesFilteredByModel(
//        @Query("q[model_cont]") model: String? = null,
//        @Query("page") page: Int = FIRST_PAGE
//    ): List<FleetioVehicle>

    @Headers(
        "Authorization: Token token=a3ddc620b35b609682192c167de1b1f3f5100387",
        "Account-Token: 798819214b"
    )
    @GET("api/v1/vehicles/{id}")
    suspend fun getVehicleDetails(@Path("id") id: String): FleetioVehicleDetails
}
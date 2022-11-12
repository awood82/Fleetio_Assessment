package com.androidandrew.fleetio_assessment.network

import com.androidandrew.fleetio_assessment.data.Driver
import com.androidandrew.fleetio_assessment.data.VehicleDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FleetioVehicleDetails(
    val id: Long,
    val name: String,
    @SerialName("default_image_url") val imageUrl: String? = null,
    @SerialName("default_image_url_small") val imageUrlSmall: String? = null,
    @SerialName("default_image_url_medium") val imageUrlMedium: String? = null,
    @SerialName("default_image_url_large") val imageUrlLarge: String? = null,
    val make: String? = null,
    val model: String? = null,
    @SerialName("vehicle_status_name") val status: String,
    @SerialName("meter_name") val meter1Name: String,
    @SerialName("current_meter_value") val meter1Value: Double,
    @SerialName("meter_unit") val meter1Units: String,
    @SerialName("secondary_meter") val meter2Exists: Boolean,
    @SerialName("secondary_meter_name") val meter2Name: String? = null,
    @SerialName("secondary_meter_value") val meter2Value: Double? = null,
    @SerialName("secondary_meter_unit") val meter2Units: String? = null,
    val driver: FleetioDriver? = null,
    val vin: String? = null,
    @SerialName("license_plate") val licensePlate: String? = null
)

fun FleetioVehicleDetails.toDomainModel(): VehicleDetails {
    return VehicleDetails(
        id = id,
        name = name,
        thumbnailUrl = imageUrlMedium,
        make = make,
        model = model,
        largeImageUrl = imageUrlLarge,
        status = status,
        meter1Name = meter1Name,
        meter1Value = meter1Value.toString(),
        meter1Units = meter1Units,
        meter2Exists = meter2Exists,
        meter2Name = meter2Name,
        meter2Value = meter2Value?.toString(),
        driver = driver?.toDomainModel(),
        vin = vin,
        licensePlate = licensePlate
    )
}
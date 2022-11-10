package com.androidandrew.fleetio_assessment.network

import com.androidandrew.fleetio_assessment.data.Vehicle
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FleetioVehicle(
    val id: Long,
    val name: String,
    @SerialName("default_image_url") val imageUrl: String? = null,
    @SerialName("default_image_url_small") val imageUrlSmall: String? = null,
    @SerialName("default_image_url_medium") val imageUrlMedium: String? = null,
    @SerialName("default_image_url_large") val imageUrlLarge: String? = null,
    val make: String? = null,
    val model: String? = null
)

fun FleetioVehicle.toDomainModel(): Vehicle {
    return Vehicle(
        id = id,
        name = name,
        thumbnailUrl = imageUrlMedium,
        make = make,
        model = model
    )
}
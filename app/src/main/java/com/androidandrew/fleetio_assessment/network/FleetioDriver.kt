package com.androidandrew.fleetio_assessment.network

import com.androidandrew.fleetio_assessment.data.Driver
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FleetioDriver(
    val id: Long,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("full_name") val fullName: String,
    @SerialName("default_image_url") val imgUrl: String? = null
)

fun FleetioDriver.toDomainModel(): Driver {
    return Driver(
        id = id,
        fullName = fullName,
        imgUrl = imgUrl
    )
}
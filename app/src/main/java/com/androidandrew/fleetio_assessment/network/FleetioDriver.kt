package com.androidandrew.fleetio_assessment.network

import com.androidandrew.fleetio_assessment.data.Driver
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FleetioDriver(
    val id: Long? = null,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("full_name") val fullName: String? = null,
    @SerialName("default_image_url") val imgUrl: String? = null
)

fun FleetioDriver.toDomainModel(): Driver? {
    return if (this.id != null) {
        Driver(
            id = id,
            fullName = fullName ?: "",
            imgUrl = imgUrl
        )
    } else {
        null
    }
}
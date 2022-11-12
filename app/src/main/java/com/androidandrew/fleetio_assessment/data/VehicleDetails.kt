package com.androidandrew.fleetio_assessment.data

data class VehicleDetails(
    val id: Long,
    val name: String,
    val thumbnailUrl: String? = null,
    val make: String? = null,
    val model: String? = null,
    val largeImageUrl: String? = null,
    val status: String,
    val meter1Name: String,
    val meter1Value: String,
    val meter1Units: String,
    val meter2Exists: Boolean,
    val meter2Name: String? = null,
    val meter2Value: String? = null,
    val meter2Units: String? = null,
    val driver: Driver? = null,
    val vin: String? = null,
    val licensePlate: String? = null
)
package com.androidandrew.fleetio_assessment.data

data class Vehicle(
    val id: Long,
    val name: String,
    val thumbnailUrl: String? = null,
    val make: String? = null,
    val model: String? = null
)

package com.androidandrew.fleetio_assessment.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class FleetioVehicleTest {

    @Test
    fun fleetioVehicle_toDomainModel_convertsToVehicle() {
        val fleetioVehicle = FleetioVehicle(
            id = Integer.MAX_VALUE.toLong() + 1,
            name = "the name",
            imageUrl = "url",
            imageUrlSmall = "urlSmall",
            imageUrlMedium = "urlMedium",
            imageUrlLarge = "urlLarge",
            make = "the make",
            model = "the model"
        )

        val vehicle = fleetioVehicle.toDomainModel()

        assertEquals(2147483648L, vehicle.id)
        assertEquals("the name", vehicle.name)
        assertEquals("urlMedium", vehicle.thumbnailUrl)
        assertEquals("the make", vehicle.make)
        assertEquals("the model", vehicle.model)
    }

    @Test
    fun fleetioVehicle_withNullValues_toDomainModel_convertsToVehicle() {
        val fleetioVehicle = FleetioVehicle(
            id = 1,
            name = "the name"
        )

        val vehicle = fleetioVehicle.toDomainModel()

        assertEquals(1, vehicle.id)
        assertEquals("the name", vehicle.name)
        assertNull(vehicle.thumbnailUrl)
        assertNull(vehicle.make)
        assertNull(vehicle.model)
    }
}
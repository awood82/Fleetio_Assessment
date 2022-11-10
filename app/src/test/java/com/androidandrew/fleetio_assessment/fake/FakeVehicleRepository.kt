package com.androidandrew.fleetio_assessment.fake

import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import com.androidandrew.fleetio_assessment.data.Vehicle
import com.androidandrew.fleetio_assessment.network.FleetioVehicle
import com.androidandrew.fleetio_assessment.network.toDomainModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FakeVehicleRepository : IVehicleRepository {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getVehicles(): List<Vehicle> {
        val vehicles = json.decodeFromString<Array<FleetioVehicle>>(FakeVehicleSource.fakeResponse)
        return vehicles.map {
            it.toDomainModel()
        }.toList()
    }
}
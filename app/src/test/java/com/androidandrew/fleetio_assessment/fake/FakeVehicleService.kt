package com.androidandrew.fleetio_assessment.fake

import com.androidandrew.fleetio_assessment.network.FleetioVehicle
import com.androidandrew.fleetio_assessment.network.IFleetioVehiclesService
import com.androidandrew.fleetio_assessment.network.json
import kotlinx.serialization.decodeFromString

class FakeVehicleService : IFleetioVehiclesService {
    override suspend fun getVehicles(): List<FleetioVehicle> {
        val vehicles = json.decodeFromString<Array<FleetioVehicle>>(FakeVehicleSource.fakeResponse)
        return vehicles.toList()
    }
}
package com.androidandrew.fleetio_assessment.fake

import com.androidandrew.fleetio_assessment.network.FleetioVehicle
import com.androidandrew.fleetio_assessment.network.FleetioVehicleDetails
import com.androidandrew.fleetio_assessment.network.IFleetioVehiclesService
import com.androidandrew.fleetio_assessment.network.IFleetioVehiclesService.Companion.json
import kotlinx.serialization.decodeFromString

class FakeVehicleService : IFleetioVehiclesService {
    override suspend fun getVehicles(makeFilter: String?, page: Int): List<FleetioVehicle> {
        val vehicles = json.decodeFromString<Array<FleetioVehicle>>(FakeVehicleSource.fakeResponse)
        return vehicles.toList()
    }

    override suspend fun getVehicleDetails(id: String): FleetioVehicleDetails {
        return json.decodeFromString<FleetioVehicleDetails>(FakeVehicleSource.fakeResponse)
    }
}
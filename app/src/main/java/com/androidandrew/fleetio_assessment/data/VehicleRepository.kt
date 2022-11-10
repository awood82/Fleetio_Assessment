package com.androidandrew.fleetio_assessment.data

import com.androidandrew.fleetio_assessment.network.FakeVehiclesService
import com.androidandrew.fleetio_assessment.network.toDomainModel

interface IVehicleRepository {
    suspend fun getVehicles(): List<Vehicle>
}

class DefaultVehicleRepository : IVehicleRepository {
    override suspend fun getVehicles(): List<Vehicle> {
//        val response = fleetioVehiclesService.getVehicles()
        val response = FakeVehiclesService.getVehicles()
        return response.map {
            it.toDomainModel()
        }
    }

}
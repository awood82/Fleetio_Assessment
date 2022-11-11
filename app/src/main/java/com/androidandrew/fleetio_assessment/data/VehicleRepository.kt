package com.androidandrew.fleetio_assessment.data

import com.androidandrew.fleetio_assessment.network.CachedVehiclesService
import com.androidandrew.fleetio_assessment.network.toDomainModel

interface IVehicleRepository {
    suspend fun getVehicles(): List<Vehicle>
}

class DefaultVehicleRepository : IVehicleRepository {
    override suspend fun getVehicles(): List<Vehicle> {
//        val response = fleetioVehiclesService.getVehicles()
        val response = CachedVehiclesService.getVehicles()
        return response.map {
            it.toDomainModel()
        }
    }

}
package com.androidandrew.fleetio_assessment.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import com.androidandrew.fleetio_assessment.data.Vehicle
import com.androidandrew.fleetio_assessment.data.VehicleDetails
import com.androidandrew.fleetio_assessment.network.FleetioVehicle
import com.androidandrew.fleetio_assessment.network.FleetioVehicleDetails
import com.androidandrew.fleetio_assessment.network.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FakeVehicleRepository : IVehicleRepository {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getVehicles(makeFilter: String?): List<Vehicle> {
        val vehicles = json.decodeFromString<Array<FleetioVehicle>>(FakeVehicleSource.fakeResponse)
        return vehicles.map {
            it.toDomainModel()
        }.toList()
    }

    override fun getVehiclesFlow(makeFilter: String?): Flow<PagingData<Vehicle>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,  // Make it smaller for testing since we have fewer items (max 100)
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FakePagingSource() }
        ).flow
    }

    override suspend fun getVehicleDetails(id: Long): VehicleDetails {
        val vehicles = json.decodeFromString<Array<FleetioVehicleDetails>>(FakeVehicleSource.fakeResponse)
        return vehicles[0].toDomainModel()
    }

    suspend fun getVehicleDetailsWithEmptyDriver(): VehicleDetails {
        val vehicleDetails = json.decodeFromString<FleetioVehicleDetails>(FakeVehicleSource.detailResponseEmptyDriver)
        return vehicleDetails.toDomainModel()
    }
}
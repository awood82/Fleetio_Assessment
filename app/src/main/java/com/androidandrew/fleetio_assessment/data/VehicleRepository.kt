package com.androidandrew.fleetio_assessment.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.androidandrew.fleetio_assessment.network.IFleetioVehiclesService
import com.androidandrew.fleetio_assessment.network.toDomainModel
import kotlinx.coroutines.flow.Flow

interface IVehicleRepository {
    suspend fun getVehicles(makeFilter: String? = null): List<Vehicle>
    fun getVehiclesFlow(makeFilter: String? = null): Flow<PagingData<Vehicle>>
    suspend fun getVehicleDetails(id: Long): VehicleDetails
}

class DefaultVehicleRepository(private val service: IFleetioVehiclesService) : IVehicleRepository {
    override suspend fun getVehicles(makeFilter: String?): List<Vehicle> {
        val response = service.getVehicles(makeFilter)
        return response.map {
            it.toDomainModel()
        }
    }

    override fun getVehiclesFlow(makeFilter: String?): Flow<PagingData<Vehicle>> {
        return Pager(
            config = PagingConfig(
                pageSize = IFleetioVehiclesService.VEHICLES_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { VehiclePagingSource(makeFilter, service) }
        ).flow
    }

    override suspend fun getVehicleDetails(id: Long): VehicleDetails {
        val response = service.getVehicleDetails(id.toString())
        return response.toDomainModel()
    }
}
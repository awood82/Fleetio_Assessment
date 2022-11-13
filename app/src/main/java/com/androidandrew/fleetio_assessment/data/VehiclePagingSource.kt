package com.androidandrew.fleetio_assessment.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.androidandrew.fleetio_assessment.network.IFleetioVehiclesService
import com.androidandrew.fleetio_assessment.network.toDomainModel

class VehiclePagingSource(
    private val makeFilter: String?,
    private val service: IFleetioVehiclesService
) : PagingSource<Int, Vehicle>() {
    override fun getRefreshKey(state: PagingState<Int, Vehicle>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vehicle> {
        val position = params.key ?: IFleetioVehiclesService.FIRST_PAGE
        try {
            val vehicles = service.getVehicles(makeFilter, position)
            val nextKey = when (vehicles.isEmpty()) {
                true -> null // The server returns an empty result set when the page > Pagination-Total-Pages
                false -> {
                    position + (params.loadSize / IFleetioVehiclesService.VEHICLES_PER_PAGE)
                }
            }
//            android.util.Log.e("PAGING", "Load. pos=$position, next=$nextKey")
            return LoadResult.Page(
                data = vehicles.map { it.toDomainModel() },
                nextKey = nextKey,
                prevKey = if (position == IFleetioVehiclesService.FIRST_PAGE) { null } else { position - 1 }
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
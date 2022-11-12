package com.androidandrew.fleetio_assessment.fake

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.androidandrew.fleetio_assessment.data.Vehicle

class FakePagingSource : PagingSource<Int, Vehicle>() {
    override fun getRefreshKey(state: PagingState<Int, Vehicle>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vehicle> {
        return LoadResult.Page<Int, Vehicle>(
            data = FakeVehicleRepository().getVehicles(),
            prevKey = null,
            nextKey = 2
        )
    }
}
package com.androidandrew.fleetio_assessment.network

import com.androidandrew.fleetio_assessment.fake.FakeVehicleService
import com.androidandrew.fleetio_assessment.fake.FakeVehicleSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class FleetioVehicleServiceTest {

    @Test
    fun serialization_ofFakeData_worksOffline() = runTest {
        val fakeService = FakeVehicleService()
        assertEquals(FakeVehicleSource.numVehicles, fakeService.getVehicles().size)
    }
}
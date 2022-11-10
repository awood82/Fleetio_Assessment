package com.androidandrew.fleetio_assessment.data

import com.androidandrew.fleetio_assessment.fake.FakeVehicleRepository
import com.androidandrew.fleetio_assessment.fake.FakeVehicleSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class VehicleRepositoryTest {

    @Test
    fun getVehicles_withFakeData_returns10Items() = runTest {
        val fakeRepo = FakeVehicleRepository()

        val vehicles = fakeRepo.getVehicles()

        assertEquals(FakeVehicleSource.numVehicles, vehicles.size)
    }
}
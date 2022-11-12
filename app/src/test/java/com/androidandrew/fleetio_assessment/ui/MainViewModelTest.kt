package com.androidandrew.fleetio_assessment.ui

import com.androidandrew.fleetio_assessment.TestDispatcherRule
import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import com.androidandrew.fleetio_assessment.fake.FakeVehicleRepository
import com.androidandrew.fleetio_assessment.fake.FakeVehicleSource
import com.androidandrew.fleetio_assessment.ui.main.MainUiState
import com.androidandrew.fleetio_assessment.ui.main.MainViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    private val mockRepo: IVehicleRepository = mockk(relaxed = true)
    private lateinit var viewModel: MainViewModel

    private fun createViewModelWithFakeRepo() {
        viewModel = MainViewModel(FakeVehicleRepository())
    }

    @Test
    fun getVehicles_state_isSuccess() {
        createViewModelWithFakeRepo()

        assertTrue(viewModel.uiState is MainUiState.Success)
    }

    @Test
    fun getVehicles_withError_stateIsError() {
        coEvery { mockRepo.getVehicles() } throws IOException("Error")
        every { mockRepo.getVehiclesFlow() } throws IOException("Error")
        viewModel = MainViewModel(mockRepo)

        assertTrue(viewModel.uiState is MainUiState.Error)
    }
}
package com.androidandrew.fleetio_assessment.ui

import com.androidandrew.fleetio_assessment.TestDispatcherRule
import com.androidandrew.fleetio_assessment.ui.main.MainUiState
import com.androidandrew.fleetio_assessment.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun getVehicles_state_isSuccess() {

        assertTrue(viewModel.uiState is MainUiState.Success)
    }
}
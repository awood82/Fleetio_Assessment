package com.androidandrew.fleetio_assessment.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidandrew.fleetio_assessment.data.DefaultVehicleRepository
import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import com.androidandrew.fleetio_assessment.data.VehicleDetails
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed interface DetailsUiState {
    data class Success(val vehicleDetails: VehicleDetails): DetailsUiState
    data class Error(val errorMessage: String): DetailsUiState
    object Loading: DetailsUiState
}

class DetailsViewModel(vehicleId: Long) : ViewModel() {

    var vehicleRepository: IVehicleRepository = DefaultVehicleRepository()
    var uiState: DetailsUiState by mutableStateOf(DetailsUiState.Loading)
    private lateinit var vehicleDetails: VehicleDetails

    init {
        viewModelScope.launch {
            uiState = DetailsUiState.Loading
            uiState = try {
                vehicleDetails = vehicleRepository.getVehicleDetails(vehicleId)
                DetailsUiState.Success(vehicleDetails)
            } catch (e: Exception) {
                e.printStackTrace()
                DetailsUiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}
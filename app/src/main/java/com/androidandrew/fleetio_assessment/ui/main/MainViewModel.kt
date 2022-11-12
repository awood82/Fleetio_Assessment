package com.androidandrew.fleetio_assessment.ui.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidandrew.fleetio_assessment.data.DefaultVehicleRepository
import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import com.androidandrew.fleetio_assessment.data.Vehicle
import kotlinx.coroutines.launch

sealed interface MainUiState {
    data class Success(val vehicles: List<Vehicle>): MainUiState
    data class Error(val errorMessage: String): MainUiState
    object Loading: MainUiState
}

class MainViewModel : ViewModel() {

    var vehicleRepository: IVehicleRepository = DefaultVehicleRepository()
    var uiState: MainUiState by mutableStateOf(MainUiState.Loading)

    init {
        viewModelScope.launch {
            uiState = MainUiState.Loading
            uiState = try {
                val response = vehicleRepository.getVehicles()
                MainUiState.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("TAG", "Error: ${e.localizedMessage}")
                MainUiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}
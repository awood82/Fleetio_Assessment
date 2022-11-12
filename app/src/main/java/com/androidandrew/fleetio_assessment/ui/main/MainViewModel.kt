package com.androidandrew.fleetio_assessment.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import com.androidandrew.fleetio_assessment.data.Vehicle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

sealed interface MainUiState {
    data class Success(val vehicles: Flow<PagingData<Vehicle>>): MainUiState
    data class Error(val errorMessage: String): MainUiState
    object Loading: MainUiState
}

class MainViewModel(private val vehicleRepository: IVehicleRepository) : ViewModel() {

    var uiState: MainUiState by mutableStateOf(MainUiState.Loading)
//    lateinit var theFlow: Flow<PagingData<Vehicle>>

    init {
        viewModelScope.launch {
            uiState = MainUiState.Loading
            uiState = try {
                val response = vehicleRepository.getVehiclesFlow()
//                val response = vehicleRepository.getVehicles()
                MainUiState.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
//                Log.e("TAG", "Error: ${e.localizedMessage}")
                MainUiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}
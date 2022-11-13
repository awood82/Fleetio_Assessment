package com.androidandrew.fleetio_assessment.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import com.androidandrew.fleetio_assessment.data.Vehicle
import kotlinx.coroutines.Job
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
    var searchJob: Job? = null

    init {
        performSearch()
    }

    fun performSearch(makeFilter: String? = null) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            uiState = MainUiState.Loading
            uiState = try {
                val response = vehicleRepository.getVehiclesFlow(makeFilter)
                MainUiState.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
//                Log.e("TAG", "Error: ${e.localizedMessage}")
                MainUiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}
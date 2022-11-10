package com.androidandrew.fleetio_assessment.ui.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidandrew.fleetio_assessment.data.DefaultVehicleRepository
import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var vehicleRepository: IVehicleRepository = DefaultVehicleRepository()
    var uiState: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            uiState = try {
                val response = vehicleRepository.getVehicles()
                val item = response[10]
                "Found ${response.size} vehicles.\n11th one is: $item"
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("TAG", "Error: ${e.localizedMessage}")
                e.localizedMessage?.toString() ?: "Generic error message"
            }
        }
    }
}
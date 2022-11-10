package com.androidandrew.fleetio_assessment.ui.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidandrew.fleetio_assessment.network.fleetioVehiclesService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var uiState: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            uiState = try {
                val response = fleetioVehiclesService.getVehicles()
                Log.d("TAG", "Received: $response")
                response
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("TAG", "Error: ${e.localizedMessage}")
                e.localizedMessage?.toString() ?: "Generic error message"
            }
        }
    }
}
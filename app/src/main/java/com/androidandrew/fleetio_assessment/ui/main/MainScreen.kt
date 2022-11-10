package com.androidandrew.fleetio_assessment.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.androidandrew.fleetio_assessment.data.Vehicle
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = MainViewModel()
    NetworkResults(results = viewModel.uiState)
}

@Composable
fun VehicleItem(
    vehicle: Vehicle,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row() {
            Image(
                painter = painterResource(android.R.drawable.ic_dialog_info),
                contentDescription = null
            )
        }
        Row() {
            Column() {
                Text(text = vehicle.name)
                if (vehicle.make != null) {
                    Text(text = vehicle.make)
                }
                if (vehicle.model != null) {
                    Text(text = vehicle.model)
                }
            }
        }
    }
}

@Composable
fun NetworkResults(results: String) {
    Text(text = results)
}

@Preview(showBackground = true)
@Composable
fun VehicleItemPreview() {
    Fleetio_AssessmentTheme {
        val vehicle = Vehicle(id = 1, name = "A vehicle", make = "Volkswagen", model = "Touareg")
        VehicleItem(vehicle = vehicle)
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkResultsPreview() {
    Fleetio_AssessmentTheme {
        NetworkResults(results = "Downloaded vehicles")
    }
}
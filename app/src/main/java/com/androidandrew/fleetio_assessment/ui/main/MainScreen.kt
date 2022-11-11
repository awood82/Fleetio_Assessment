package com.androidandrew.fleetio_assessment.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.androidandrew.fleetio_assessment.R
import com.androidandrew.fleetio_assessment.data.Vehicle
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = MainViewModel()
    when (val state = viewModel.uiState) {
        is MainUiState.Success -> { VehicleGrid(vehicles = state.vehicles) }
        is MainUiState.Loading -> { NetworkResults(results = stringResource(R.string.loading)) }
        is MainUiState.Error -> { NetworkResults(results = state.errorMessage) }
    }
}

@Composable
fun VehicleGrid(
    vehicles: List<Vehicle>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // TODO: Adaptive? What size are these thumbnails?
        modifier = modifier.fillMaxWidth()
    ) {
        items(
            items = vehicles,
            key = { vehicle -> vehicle.id }
        ) { vehicle ->
            VehicleItem(vehicle = vehicle)
        }
    }
}

@Composable
fun VehicleItem(
    vehicle: Vehicle,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row() {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(vehicle.thumbnailUrl)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(android.R.drawable.ic_dialog_info),
                error = painterResource(android.R.drawable.ic_dialog_alert)
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
fun NetworkResults(
    results: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(text = results)
    }
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
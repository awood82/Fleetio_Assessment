package com.androidandrew.fleetio_assessment.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidandrew.fleetio_assessment.R
import com.androidandrew.fleetio_assessment.data.Vehicle
import com.androidandrew.fleetio_assessment.ui.component.LoadingScreen
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme

@Composable
fun MainScreen(
    onVehicleClicked: (Vehicle) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = MainViewModel()
    when (val state = viewModel.uiState) {
        is MainUiState.Success -> {
            VehicleGrid(
                vehicles = state.vehicles,
                onVehicleClicked = onVehicleClicked
            )
        }
        is MainUiState.Loading -> { LoadingScreen() }
        is MainUiState.Error -> { NetworkResults(results = state.errorMessage) }
    }
}

@Composable
fun VehicleGrid(
    vehicles: List<Vehicle>,
    onVehicleClicked: (Vehicle) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1), // TODO: Adaptive? What size are these thumbnails?
        modifier = modifier.fillMaxWidth()
    ) {
        items(
            items = vehicles,
            key = { vehicle -> vehicle.id }
        ) { vehicle ->
            VehicleItem(
                vehicle = vehicle,
                modifier = modifier
                    .clickable(onClick = { onVehicleClicked(vehicle) }
                )
            )
        }
    }
}

@Composable
fun VehicleItem(
    vehicle: Vehicle,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.default_spacing)),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.default_padding))
            .testTag(vehicle.id.toString())
    ) {
//            AsyncImage(
//                model = ImageRequest.Builder(context = LocalContext.current)
//                    .data(vehicle.thumbnailUrl)
//                    .build(),
//                contentDescription = null,
//                placeholder = painterResource(R.drawable.ic_loading_image),
//                error = painterResource(R.drawable.ic_error_image)
//            )
        Image(
            painter = painterResource(R.drawable.ic_loading_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp)
                .testTag(vehicle.thumbnailUrl ?: "")
        )
        Column() {
            Text(text = vehicle.name, style = MaterialTheme.typography.titleMedium)
            if (vehicle.make != null) {
                Text(text = vehicle.make, style = MaterialTheme.typography.bodyMedium)
            }
            if (vehicle.model != null) {
                Text(text = vehicle.model, style = MaterialTheme.typography.bodyMedium)
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
        val vehicle = Vehicle(id = 1, name = "A man has no name", make = "Volkswagen", model = "Touareg")
        VehicleItem(vehicle = vehicle)
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkResultsPreview() {
    Fleetio_AssessmentTheme {
        NetworkResults(results = "Loading...")
    }
}
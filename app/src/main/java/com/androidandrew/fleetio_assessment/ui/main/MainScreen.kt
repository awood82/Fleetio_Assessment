package com.androidandrew.fleetio_assessment.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.androidandrew.fleetio_assessment.R
import com.androidandrew.fleetio_assessment.data.Vehicle
import com.androidandrew.fleetio_assessment.ui.component.LoadingScreen
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme
import org.koin.androidx.compose.get

@Composable
fun MainScreen(
    onVehicleClicked: (Vehicle) -> Unit,
    viewModel: MainViewModel = get(),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SearchBar(onSearchRequested = { viewModel.performSearch(it) })
        when (val state = viewModel.uiState) {
            is MainUiState.Success -> {
                VehicleGrid(
                    pagedVehicles = state.vehicles.collectAsLazyPagingItems(),
                    onVehicleClicked = onVehicleClicked
                )
            }
            is MainUiState.Loading -> {
                LoadingScreen()
            }
            is MainUiState.Error -> {
                NetworkResults(results = state.errorMessage)
            }
        }
    }
}

@Composable
fun VehicleGrid(
    pagedVehicles: LazyPagingItems<Vehicle>,
    onVehicleClicked: (Vehicle) -> Unit,
    modifier: Modifier = Modifier
) {
//    android.util.Log.e("VehicleGrid", "flow size = ${pagedVehicles.itemCount}")
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(
            items = pagedVehicles,
            key = { vehicle -> vehicle.id }
        ) { vehicle ->
            VehicleItem(
                vehicle = vehicle!!,
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
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(vehicle.thumbnailUrl)
                .build(),
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_loading_image),
            error = painterResource(R.drawable.ic_error_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .testTag(vehicle.thumbnailUrl ?: "")
        )
//        Image(
//            painter = painterResource(R.drawable.ic_loading_image),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(80.dp)
//                .testTag(vehicle.thumbnailUrl ?: "")
//        )
        Column {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearchRequested: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchTerms by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchTerms,
        onValueChange = { searchTerms = it },
        singleLine = true,
        placeholder = { Text(stringResource(R.string.search_filter_by_make)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon),
                modifier = modifier.clickable {
                    onSearchRequested(searchTerms)
                }
            )
        },
        keyboardActions = KeyboardActions(
            onDone = { onSearchRequested(searchTerms) }
        ),
        modifier = modifier.fillMaxWidth()
    )
}


@Preview(showBackground = true)
@Composable
fun SearchBarPreview(
    modifier: Modifier = Modifier
) {
    Fleetio_AssessmentTheme {
        SearchBar({})
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
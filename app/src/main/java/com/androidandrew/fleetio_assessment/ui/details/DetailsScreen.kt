package com.androidandrew.fleetio_assessment.ui.details

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.androidandrew.fleetio_assessment.R
import com.androidandrew.fleetio_assessment.data.VehicleDetails
import com.androidandrew.fleetio_assessment.ui.component.LoadingScreen
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme
import org.koin.androidx.compose.get
import org.koin.core.parameter.parametersOf

// https://developer.android.com/jetpack/compose/navigation strongly recommends against passing a complex argument,
// so this screen is only accepting a Long for the ID and not a Vehicle or more complex data type.
// Weird... This wasn't an issue with the Navigation library before when connecting Fragments and using SafeArgs.

@Composable
fun DetailsScreen(
    vehicleId: Long, //TODO: Vehicle or VehicleDetails?
    viewModel: DetailsViewModel = get { parametersOf(vehicleId) },
    modifier: Modifier = Modifier
) {
    when (val state = viewModel.uiState) {
        is DetailsUiState.Success -> {
            VehicleDetailsSuccess(details = state.vehicleDetails)
        }
        is DetailsUiState.Loading -> { LoadingScreen() }
        is DetailsUiState.Error -> {}
    }
}

@Composable
fun VehicleDetailsSuccess(
    details: VehicleDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.default_padding))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(details.largeImageUrl)
                .build(),
            contentDescription = stringResource(R.string.thumbnail),
            placeholder = painterResource(R.drawable.ic_loading_image),
            error = painterResource(R.drawable.ic_error_image),
            contentScale = ContentScale.FillWidth,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.name_details, details.name),
            style = MaterialTheme.typography.titleLarge
        )
        DetailsText(textWithArgs = R.string.make_details, details.make)
        DetailsText(textWithArgs = R.string.model_details, details.model)
        DetailsText(textWithArgs = R.string.status_details, details.status)
        DetailsText(textWithArgs = R.string.driver_details, details.driver?.fullName)
        MeterText(
            textWithArgs = R.string.meter_details,
            name = details.meter1Name,
            value = details.meter1Value,
            units = details.meter1Units
        )
        if (details.meter2Exists) {
            MeterText(
                textWithArgs = R.string.meter_details,
                name = details.meter2Name,
                value = details.meter2Value,
                units = details.meter2Units
            )
        }
        DetailsText(textWithArgs = R.string.vin_details, details.vin)
        DetailsText(textWithArgs = R.string.license_plate_details, details.licensePlate)
    }
}

@Composable
fun DetailsText(
    @StringRes textWithArgs: Int,
    value: String?,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(textWithArgs, value ?: stringResource(R.string.unknown)),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun MeterText(
    @StringRes textWithArgs: Int,
    name: String?,
    value: String?,
    units: String?,
    modifier: Modifier = Modifier
) {
    val unknown = stringResource(R.string.unknown)
    Text(
        text = stringResource(
            textWithArgs,
            name ?: unknown,
            value ?: unknown,
            units ?: unknown
        ),
        style = MaterialTheme.typography.bodyLarge
    )
}


@Preview(showBackground = true)
@Composable
fun VehicleDetailsSuccessPreview() {
    Fleetio_AssessmentTheme {
        val details = VehicleDetails(
            id = 1L,
            name = "Vehicle o' doom",
            make = "Giant",
            model = "Bumper Car",
            status = "Damaged",
            meter1Name = "Odometer",
            meter1Value = "10",
            meter1Units = "mi",
            meter2Exists = true,
            meter2Name = "Crash Count",
            meter2Value = "13489743",
            meter2Units = "crashes",
            driver = null,
            vin = "12324234",
            licensePlate = "3HAHAHA"
        )
        VehicleDetailsSuccess(details = details)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsTextPreview() {
    Fleetio_AssessmentTheme {
        DetailsText(
            textWithArgs = R.string.make_details,
            value = "Honda"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsTextUnknownPreview() {
    Fleetio_AssessmentTheme {
        DetailsText(
            textWithArgs = R.string.make_details,
            value = null
        )
    }
}
package com.androidandrew.fleetio_assessment.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// https://developer.android.com/jetpack/compose/navigation strongly recommends against passing a complex argument,
// so this screen is only accepting a Long for the ID and not a Vehicle or more complex data type.
// Weird... This wasn't an issue with the Navigation library before when connecting Fragments and using SafeArgs.

@Composable
fun DetailsScreen(
    vehicleId: Long, //TODO: Vehicle or VehicleDetails?
    modifier: Modifier = Modifier
) {
    Text("Details screen. ID = $vehicleId")
}
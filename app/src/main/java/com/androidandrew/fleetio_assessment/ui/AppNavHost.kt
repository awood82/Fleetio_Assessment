package com.androidandrew.fleetio_assessment.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.androidandrew.fleetio_assessment.ui.details.DetailsScreen
import com.androidandrew.fleetio_assessment.ui.main.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "main", // TODO: remove hard-coded string
        modifier = modifier
    ) {
        composable(route = "main") {
            MainScreen(
                onVehicleClicked = { navController.navigate("details/${it.id}") },
                modifier = modifier
            )
        }
        composable(
            route = "details/{vehicleId}",
            arguments = listOf(navArgument("vehicleId") { type = NavType.LongType } )
        ) { backStackEntry ->
            DetailsScreen(
                backStackEntry.arguments?.getLong("vehicleId") ?: 0,
                modifier = modifier
            )
        }
    }
}
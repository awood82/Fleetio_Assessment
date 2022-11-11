package com.androidandrew.fleetio_assessment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidandrew.fleetio_assessment.ui.main.MainScreen
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {
        NavHost(
            navController = navController,
            startDestination = "main", // TODO: remove hard-coded string
            modifier = modifier
        ) {
            composable("main") {
                MainScreen(
                    modifier = modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    Fleetio_AssessmentTheme {
        MainApp()
    }
}
package com.androidandrew.fleetio_assessment.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme

@Composable
fun MainApp(
    modifier: Modifier = Modifier
) {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {
        AppNavHost()
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    Fleetio_AssessmentTheme {
        MainApp()
    }
}
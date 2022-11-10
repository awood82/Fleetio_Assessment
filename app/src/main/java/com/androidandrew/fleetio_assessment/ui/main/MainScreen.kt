package com.androidandrew.fleetio_assessment.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = MainViewModel()
    NetworkResults(results = viewModel.uiState)
}

@Composable
fun NetworkResults(results: String) {
    Text(text = results)
}

@Preview(showBackground = true)
@Composable
fun NetworkResultsPreview() {
    Fleetio_AssessmentTheme {
        NetworkResults(results = "Downloaded vehicles")
    }
}
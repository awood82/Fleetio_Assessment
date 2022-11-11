package com.androidandrew.fleetio_assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.androidandrew.fleetio_assessment.ui.main.MainScreen
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fleetio_AssessmentTheme {
                MainApp()
            }
        }
    }
}
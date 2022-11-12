package com.androidandrew.fleetio_assessment.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidandrew.fleetio_assessment.R
import com.androidandrew.fleetio_assessment.ui.theme.Fleetio_AssessmentTheme

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_loading_image),
            contentDescription = stringResource(R.string.loading),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(440.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    Fleetio_AssessmentTheme {
        LoadingScreen()
    }
}
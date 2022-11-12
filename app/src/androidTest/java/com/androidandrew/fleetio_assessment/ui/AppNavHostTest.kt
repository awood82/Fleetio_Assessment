package com.androidandrew.fleetio_assessment.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppNavHostTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
        }
    }

    @Test
    fun startDestination_isMainScreen() {
        assertEquals("main", navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun startDestination_clickItem_navigatesToDetailsScreen() {
        val expectedTag = 2128326L
        composeTestRule.onNodeWithTag(expectedTag.toString()).performClick()

        assertEquals("details/{vehicleId}", navController.currentBackStackEntry?.destination?.route)
        assertEquals(expectedTag, navController.currentBackStackEntry?.arguments?.getLong("vehicleId"))
    }
}
package com.androidandrew.fleetio_assessment.ui.main

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.androidandrew.fleetio_assessment.data.Vehicle
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testId = 1L
    private val testName = "test vehicle"
    private val testUrl = "url"
    private val testMake = "test make"
    private val testModel = "test model"
    private val testVehicle = Vehicle(
        id = testId,
        name = testName,
        thumbnailUrl = testUrl,
        make = testMake,
        model = testModel
    )

    @Test
    fun vehicleItem_whenAllArgumentsAreSet_showsAll() {
        composeTestRule.setContent {
            VehicleItem(vehicle = testVehicle)
        }

        composeTestRule.onNodeWithTag(testId.toString()).assertExists()
        composeTestRule.onNodeWithText(testName).assertExists()
        composeTestRule.onNodeWithText(testMake).assertExists()
        composeTestRule.onNodeWithText(testModel).assertExists()
        composeTestRule.onNodeWithTag(testUrl).assertExists()
    }

    @Test
    fun vehicleItem_whenNullableArgumentsAreNull_hidesThem() {
        composeTestRule.setContent {
            VehicleItem(vehicle = testVehicle.copy(
                thumbnailUrl = null,
                make = null,
                model = null
            ))
        }

        composeTestRule.onNodeWithTag(testId.toString()).assertExists()
        composeTestRule.onNodeWithText(testName).assertExists()
        composeTestRule.onNodeWithText(testMake).assertDoesNotExist()
        composeTestRule.onNodeWithText(testModel).assertDoesNotExist()
        composeTestRule.onNodeWithTag(testUrl).assertDoesNotExist()
    }
}
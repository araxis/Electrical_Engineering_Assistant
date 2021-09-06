package com.vm.eea.ui.motor.motorDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.PropertyList


@Composable
fun MotorDetailsScreen(viewModel: MotorDetailsViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor Settings") {
        PropertyList(items = state.items, onItemClick = {viewModel.onItemSelect(it)})
    }
}



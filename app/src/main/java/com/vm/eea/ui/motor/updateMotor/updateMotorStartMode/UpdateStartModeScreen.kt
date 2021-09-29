package com.vm.eea.ui.motor.updateMotor.updateMotorStartMode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.GridSelector

@Composable
fun UpdateStartModeScreen(viewModel: UpdateStartModeViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Start mode") {
        Column(
            Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            GridSelector(items = state.items, onSelect = {viewModel.onValueChange(it)})
        }
    }
}
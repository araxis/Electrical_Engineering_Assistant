package com.vm.eea.ui.motor.updateMotor.updateMotorPower

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.PowerInput

@Composable
fun UpdateMotorPowerScreen(viewModel: UpdateMotorPowerViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Motor Power",canSubmit = state.canSubmit,onSubmit = {viewModel.submit()}) {
        Column(Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            PowerInput(label = "power",
                value = state.power.value,
                unit = state.power.second,
                error = state.power.error,
                onChange = { s, u -> viewModel.onPowerChange(s, u) })
        }
    }

}
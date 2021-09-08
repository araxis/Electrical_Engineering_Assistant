package com.vm.eea.ui.motor.updateMotor.updateMotorRelationAmbientTemperature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.TemperatureInput
import com.vm.eea.ui.components.VoltDropInput

@Composable
fun UpdateMotorRelationAmbientTemperatureScreen(viewModel: UpdateMotorRelationAmbientTemperatureViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Maximum voltage drop",canSubmit = state.canSubmit,onSubmit = {viewModel.submit()}) {
       Column(Modifier
           .fillMaxSize()
           .padding(16.dp)) {
           TemperatureInput(label = "Ambient Temperature",
               value = state.temperature.value,
               unit=state.temperature.second,
               error = state.temperature.error,
               onChange = {s,u-> viewModel.onValueChange(s,u) })
       }

    }
}
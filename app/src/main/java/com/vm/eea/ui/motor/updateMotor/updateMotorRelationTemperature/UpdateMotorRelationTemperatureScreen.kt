package com.vm.eea.ui.motor.updateMotor.updateMotorRelationTemperature

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

@Composable
fun UpdateMotorRelationTemperatureScreen(viewModel: UpdateMotorRelationTemperatureViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = state.pageTitle,canSubmit = state.canSubmit,onSubmit = {viewModel.submit()}) {
       Column(Modifier
           .fillMaxSize()
           .padding(16.dp)) {
           TemperatureInput(label = "Temperature",
               value = state.temperature.value,
               unit=state.temperature.second,
               error = state.temperature.error,
               onChange = {s,u-> viewModel.onValueChange(s,u) })
       }

    }
}
package com.vm.eea.ui.motor.updateMotor.updateMotorRelationSoilResistivity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.ThermalResistivityInput

@Composable
fun UpdateMotorRelationSoilResistivityScreen(viewModel: UpdateMotorRelationThermalResistivityViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Ground temperature",canSubmit = state.canSubmit,onSubmit = {viewModel.submit()}) {
       Column(Modifier
           .fillMaxSize()
           .padding(16.dp)) {
           ThermalResistivityInput(label = "Ambient Temperature",
               value = state.resistivity.value,
               unit=state.resistivity.second,
               error = state.resistivity.error,
               onChange = {s,u-> viewModel.onValueChange(s,u) })
       }

    }
}
package com.vm.eea.ui.motor.updateMotor.updateMotorRelationMaxVoltDrop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.VoltDropInput

@Composable
fun UpdateMotorRelationMaxVoltDropScreen(viewModel: UpdateMotorRelationMaxVoltDropViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Maximum voltage drop",canSubmit = state.canSubmit,onSubmit = {viewModel.submit()}) {
       Column(Modifier
           .fillMaxSize()
           .padding(16.dp)) {
           VoltDropInput(label = "Voltage drop",
               value = state.voltDrop.value,
               error = state.voltDrop.error,
               onChange = { viewModel.onValueChange(it) })
       }

    }
}
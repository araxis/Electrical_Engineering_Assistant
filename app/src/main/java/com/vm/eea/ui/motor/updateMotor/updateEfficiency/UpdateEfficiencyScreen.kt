package com.vm.eea.ui.motor.updateMotor.updateEfficiency

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.EfficiencyInput
import com.vm.eea.ui.components.FullPageDialog

@Composable
fun UpdateEfficiencyScreen(viewModel: UpdateEfficiencyViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Motor Efficiency",canSubmit = state.canSubmit,onSubmit = {viewModel.submit()}) {
        Column(Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            EfficiencyInput(
                label = "Efficiency",
                value = state.efficiency.value,
                error =state.efficiency.error ,
                onChange ={viewModel.onChange(it)} )
        }
    }
}
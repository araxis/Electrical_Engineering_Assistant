package com.vm.eea.ui.motor.updateMotor.updateEfficiency

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.StringInput

@Composable
fun UpdateEfficiencyScreen(viewModel: UpdateEfficiencyViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Motor Efficiency",canSubmit = state.canSubmit,onSubmit = {viewModel.submit()}) {
        Column(Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            StringInput(
                label = "Efficiency",
                field = state.efficiency,
                onChange ={viewModel.onChange(it)} )
        }
    }
}
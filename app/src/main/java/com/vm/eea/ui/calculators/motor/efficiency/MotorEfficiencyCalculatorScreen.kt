package com.vm.eea.ui.calculators.motor.efficiency

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.calculators.ResultDisplay
import com.vm.eea.ui.components.*
import com.vm.eea.utilities.CosPhi
import com.vm.eea.utilities.display

@Composable
fun MotorEfficiencyCalculatorScreen(viewModel: MotorEfficiencyCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor efficiency") {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            ResultDisplay(state.state,mapToString={it.display()})
            PowerInput(label = "Power",field = state.power,onValueChange = { s, u -> viewModel.onPowerChange(s, u) })
            WorkingVoltageInput(field = state.voltage,onValueChange = { i, s -> viewModel.onVoltageChange(i, s) })
            CurrentInput(label = "Current", field = state.current){s,u->viewModel.onCurrentChanged(s,u)}
            CosPhiInput(label = CosPhi, field = state.cosPhi, onChange ={viewModel.onCosPhiChange(it)} )

        }
    }
}





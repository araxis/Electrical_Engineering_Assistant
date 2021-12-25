package com.vm.eea.ui.calculators.motor.current

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

@Composable
fun MotorCurrentCalculatorScreen(viewModel: MotorCurrentCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor current") {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            ResultDisplay(state.state,mapToString={it.toFormatString()})

            PowerInput(label = "Power",field = state.power,onValueChange = { s, u -> viewModel.onPowerChange(s, u) })
            WorkingVoltageInput(field = state.voltage,onValueChange = { i, s -> viewModel.onVoltageChange(i, s) })
            CosPhiInput(label = CosPhi, field = state.cosPhi, onChange ={viewModel.onCosPhiChanged(it)} )
            EfficiencyInput(label = "Efficiency", field = state.efficiency, onChange ={viewModel.onEfficiencyChange(it)} )

        }
    }
}





package com.vm.eea.ui.calculators.main.currentCalculator

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
fun CurrentCalculatorScreen(viewModel: CurrentCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Current") {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            ResultDisplay(state.state,mapToString={it.toFormatString()})

            PowerInput(label = "input",field = state.power,onValueChange = { s, u -> viewModel.onPowerChange(s, u) })
            Row(verticalAlignment = Alignment.CenterVertically) {
                VoltageInput(label = "input",modifier = Modifier.weight(1f),field = state.voltage,onValueChange = { s, u->viewModel.onVoltageChange(s,u)})
                PowerSystemPicker(value = state.system,modifier = Modifier.padding(start = 8.dp), onClicked = {viewModel.onSystemChange(it)})
            }
            CosPhiInput(label = CosPhi, field = state.cosPhi, onChange ={viewModel.onCosPhiChanged(it)} )
            EfficiencyInput(label = "Efficiency", field = state.efficiency, onChange ={viewModel.onEfficiencyChange(it)} )

        }
    }
}





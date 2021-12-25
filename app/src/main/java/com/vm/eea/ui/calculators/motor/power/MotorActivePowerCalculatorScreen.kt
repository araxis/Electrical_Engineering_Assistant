package com.vm.eea.ui.calculators.motor.power

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.calculators.ResultDisplay

import com.vm.eea.ui.components.*
import com.vm.eea.utilities.CosPhi
import com.vm.eea.utilities.displayOrZero

@Composable
fun MotorActivePowerCalculatorScreen(viewModel: MotorActivePowerCalculatorViewModel){
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor power") {
        Column(modifier = Modifier.padding(8.dp)) {
            ResultDisplay(state = state.state,mapToString={it.displayOrZero()})
            WorkingVoltageInput(field = state.voltage,onValueChange = { i, s -> viewModel.onVoltageChange(i, s) })
            CurrentInput(label = "Current", field = state.current, onValueChange = {s,u->viewModel.onCurrentChange(s,u)})
            CosPhiInput(label = CosPhi, field = state.cosPhi, onChange = {viewModel.onCosPhiChanged(it)})
            EfficiencyInput(label = "Efficiency",field = state.efficiency,onChange = {  viewModel.onEfficiencyChange(it) })
        }
    }

}
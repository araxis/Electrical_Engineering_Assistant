package com.vm.eea.ui.calculators.main.activePowerCalculator.currentImpedance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.calculators.main.activePowerCalculator.DisplayActivePowerResult

import com.vm.eea.ui.components.*
import com.vm.eea.utilities.CosPhi

@Composable
fun CurrentImpedanceScreen(viewModel: CurrentImpedanceActivePowerViewModel, onInputChangeRequest:()->Unit) {
    val state by viewModel.container.stateFlow.collectAsState()
    Column {
        DisplayActivePowerResult(state = state.state)
        FormItemSelector(name = "Input",value = state.inputType.description){ onInputChangeRequest() }
        Row(verticalAlignment = Alignment.CenterVertically) {
            CurrentInput(label = "Current",modifier = Modifier.weight(1f),field = state.current,onValueChange = { s, u->viewModel.onCurrentChange(s,u)})
            PowerSystemPicker(value = state.system,modifier = Modifier.padding(start = 8.dp), onClicked = {viewModel.onSystemChange(it)})
        }
        ImpedanceInput(label = "Impedance", field = state.impedance, onChange = {s,u->viewModel.onImpedanceChange(s,u)})
        CosPhiInput(label = CosPhi, field = state.cosPhi, onChange = { viewModel.onCosPhiChanged(it) })
    }
}
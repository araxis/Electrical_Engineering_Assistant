package com.vm.eea.ui.calculators.main.activePowerCalculator.apparentCosPhi

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.calculators.main.activePowerCalculator.DisplayActivePowerResult
import com.vm.eea.ui.components.ApparentPowerInput
import com.vm.eea.ui.components.CosPhiInput
import com.vm.eea.ui.components.FormItemSelector
import com.vm.eea.utilities.CosPhi

@Composable
fun ApparentPowerCosPhiActivePowerScreen(viewModel:ApparentPowerCosPhiActivePowerViewModel, onInputChangeRequest:()->Unit) {
  val state by viewModel.container.stateFlow.collectAsState()
    Column {
        DisplayActivePowerResult(state = state.state)
        FormItemSelector(name = "Input",value = state.inputType.description){ onInputChangeRequest() }
        ApparentPowerInput(label = "Apparent input", field = state.apparentPower, onValueChange = {s,u->viewModel.onApparentPowerChange(s,u)})
        CosPhiInput(label = CosPhi, field = state.cosPhi, onChange = { viewModel.onCosPhiChanged(it) })
    }

}
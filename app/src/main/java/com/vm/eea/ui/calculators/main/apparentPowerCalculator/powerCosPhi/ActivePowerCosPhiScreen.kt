package com.vm.eea.ui.calculators.main.apparentPowerCalculator.powerCosPhi

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.DisplayApparentPowerResult
import com.vm.eea.ui.components.CosPhiInput
import com.vm.eea.ui.components.FormItemSelector
import com.vm.eea.ui.components.PowerInput
import com.vm.eea.utilities.CosPhi

@Composable
fun ActivePowerCosPhiScreen(viewModel: ActivePowerCosPhiApparentPowerViewModel, onInputChangeRequest:()->Unit) {
    val state by viewModel.container.stateFlow.collectAsState()
    Column {
        DisplayApparentPowerResult(state = state.state)
        FormItemSelector(name = "Input",value = state.inputType.description){ onInputChangeRequest() }
        PowerInput(label = "Reactive input",field = state.power){s,u->viewModel.onActivePowerChange(s,u)}
        CosPhiInput(label = CosPhi, field = state.cosPhi, onChange = {viewModel.onCosPhiChanged(it)})
    }
}

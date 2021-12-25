package com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveCosPhi

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.calculators.main.activePowerCalculator.DisplayActivePowerResult
import com.vm.eea.ui.components.CosPhiInput
import com.vm.eea.ui.components.FormItemSelector
import com.vm.eea.ui.components.ReactivePowerInput
import com.vm.eea.utilities.CosPhi

@Composable
fun ReactiveCosPhiScreen(viewModel: ReactivePowerCosPhiActivePowerViewModel, onInputChangeRequest:()->Unit) {
    val state by viewModel.container.stateFlow.collectAsState()
    Column {
        DisplayActivePowerResult(state = state.state)
        FormItemSelector(name = "Input",value = state.inputType.description){ onInputChangeRequest() }
        ReactivePowerInput(label = "Reactive input",field = state.reactivePowerField){s,u->viewModel.onReactivePowerChange(s,u)}
        CosPhiInput(label = CosPhi, field = state.cosPhi, onChange = {viewModel.onCosPhiChanged(it)})
    }
}

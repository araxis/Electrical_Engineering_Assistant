package com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveApparent

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.calculators.main.activePowerCalculator.DisplayActivePowerResult

import com.vm.eea.ui.components.*

@Composable
fun ReactiveApparentScreen(viewModel: ReactiveApparentActivePowerViewModel, onInputChangeRequest:()->Unit) {
    val state by viewModel.container.stateFlow.collectAsState()
    Column {
        DisplayActivePowerResult(state = state.state)
        FormItemSelector(name = "Input",value = state.inputType.description){ onInputChangeRequest() }
       ReactivePowerInput(label = "Reactive input",field = state.reactivePowerField){s,u->viewModel.onReactivePowerChange(s,u)}
       ApparentPowerInput(label = "Apparent input", field = state.apparentPower){s,u->viewModel.onApparentPowerChange(s,u)}
    }
}

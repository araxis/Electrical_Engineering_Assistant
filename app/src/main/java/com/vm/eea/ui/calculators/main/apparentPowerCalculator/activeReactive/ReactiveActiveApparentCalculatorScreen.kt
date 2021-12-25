package com.vm.eea.ui.calculators.main.apparentPowerCalculator.activeReactive

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.DisplayApparentPowerResult
import com.vm.eea.ui.components.FormItemSelector
import com.vm.eea.ui.components.PowerInput
import com.vm.eea.ui.components.ReactivePowerInput

@Composable
fun ReactiveActiveApparentCalculatorScreen(viewModel: ReactiveActivePowerApparentCalculatorViewModel, onInputChangeRequest:()->Unit) {
    val state by viewModel.container.stateFlow.collectAsState()
    Column {
        DisplayApparentPowerResult(state = state.state)
        FormItemSelector(name = "Input",value = state.inputType.description){ onInputChangeRequest() }
       ReactivePowerInput(label = "Reactive input",field = state.reactivePower){s,u->viewModel.onReactivePowerChange(s,u)}
       PowerInput(label = "Apparent input", field = state.activePower){s,u->viewModel.onActivePowerChange(s,u)}
    }
}

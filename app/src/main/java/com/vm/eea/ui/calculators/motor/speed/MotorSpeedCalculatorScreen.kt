package com.vm.eea.ui.calculators.motor.speed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.calculators.ResultDisplay
import com.vm.eea.ui.components.FrequencyInput
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.SlipfactorInput
import com.vm.eea.ui.components.StringInput
import com.vm.eea.utilities.displayOrZero

@Composable
fun MotorSpeedCalculatorScreen(viewModel: MotorSpeedCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor Speed") {
        Column(modifier = Modifier.padding(8.dp)) {
            ResultDisplay(state.state,succeedContent = {
                Column(Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Synchronous speed: ${it.synchronousSpeed.displayOrZero()}",modifier = Modifier.wrapContentWidth(),)
                    Text(text = "Real speed: ${it.realSpeed.displayOrZero()}",modifier = Modifier.wrapContentWidth())

                }
            })
            StringInput(label = "Number of poles",field = state.numberOfPoles) { viewModel.onPolesChange(it) }
            FrequencyInput(label = "Frequency",field = state.frequency) { s, u -> viewModel.onFrequencyChange(s, u) }
            SlipfactorInput(label = "Slip factor",field = state.slipFactor) { viewModel.onSlipFactorChanged(it) }
        }
    }

}



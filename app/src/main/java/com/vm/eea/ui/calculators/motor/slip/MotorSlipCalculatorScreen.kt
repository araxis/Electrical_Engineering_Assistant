package com.vm.eea.ui.calculators.motor.slip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vm.eea.application.format
import com.vm.eea.ui.calculators.ResultDisplay
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.SpeedInput
import com.vm.eea.utilities.displayOrZero

@Composable
fun MotorSlipCalculatorScreen(viewModel: MotorSlipCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor slip") {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            ResultDisplay(state.state,succeedContent= {
                Column {
                    Text(text = it.slipFactor.displayOrZero(),modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                    Text(text = it.slipSpeed.displayOrZero(),modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                }
            })

            SpeedInput(label = "Synchronous speed",field = state.syncSpeed) { viewModel.onSyncSpeedChange(it) }

            SpeedInput(label = "Rotor speed", field = state.rotorSpeed){viewModel.onRotorSpeedChange(it)}

        }
    }
}


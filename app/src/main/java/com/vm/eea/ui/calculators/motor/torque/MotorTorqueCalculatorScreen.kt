package com.vm.eea.ui.calculators.motor.torque

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.calculators.ResultDisplay
import com.vm.eea.ui.components.*
import com.vm.eea.utilities.displayOrZero

@Composable
fun MotorTorqueCalculatorScreen(viewModel: MotorTorqueCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor torque") {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            ResultDisplay(state.state,mapToString={it.displayOrZero()})

            PowerInput(label = "Power",field = state.power) {s,u-> viewModel.onPowerChange(s,u) }

            SpeedInput(label = "Rotor speed", field = state.speed){viewModel.onSpeedChange(it)}

        }
    }
}



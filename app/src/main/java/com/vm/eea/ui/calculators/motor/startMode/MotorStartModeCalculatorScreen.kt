package com.vm.eea.ui.calculators.motor.startMode

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

@Composable
fun MotorStartModeCalculatorScreen(viewModel: MotorStartModeCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor start mode") {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            ResultDisplay(state.state,mapToString={it.name})

            PowerInput(label = "Power",field = state.power) {s,u-> viewModel.onPowerChange(s,u) }



        }
    }
}


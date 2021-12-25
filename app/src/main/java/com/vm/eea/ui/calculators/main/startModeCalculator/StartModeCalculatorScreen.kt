package com.vm.eea.ui.calculators.main.startModeCalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun StartModeCalculatorScreen(viewModel: StartModeCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()

}
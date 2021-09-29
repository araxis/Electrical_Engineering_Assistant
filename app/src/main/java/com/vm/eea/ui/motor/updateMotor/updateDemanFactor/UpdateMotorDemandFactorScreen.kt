package com.vm.eea.ui.motor.updateMotor.updateDemanFactor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.StringInput

@Composable
fun UpdateMotorDemandFactorScreen(viewMode: UpdateMotorDemandFactorViewModel) {
    val state by viewMode.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Motor demand factor",canSubmit = state.canSubmit,onSubmit = {viewMode.submit()}) {
        Column(Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            StringInput(label = "Demand factor",
                field = state.demandFactor,
                onChange = { viewMode.onChange(it) })
        }
    }
}
package com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.LengthInput

@Composable
fun UpdateMotorFeedLineLengthScreen(viewModel: UpdateMotorFeedLineLengthViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Line Length",canSubmit = state.canSubmit,onSubmit = {viewModel.submit()}) {
        Column(Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            LengthInput(label = "Length",
                value = state.length.value,
                unit = state.length.second,
                error = state.length.error,
                onChange = { s, u -> viewModel.onValueChange(s, u) })
        }
    }
}
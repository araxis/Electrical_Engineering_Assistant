package com.vm.eea.ui.motor.updateMotor.updateMotorCode

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
fun UpdateMotorCodeScreen(viewModel: UpdateMotorCodeViewModel){
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Motor Code",onSubmit = {viewModel.submit()},canSubmit = state.canSubmit) {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxSize()) {
                StringInput(label = "Code",
                    value = state.code.value,
                    error = state.code.error,
                    onChange = { viewModel.onCodeChange(it) })
                StringInput(label = "Description",
                    value = state.description.value,
                    error = null,
                    onChange = { viewModel.onDescriptionChange(it) })
            }
        }
    }
}


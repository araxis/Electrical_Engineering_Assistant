package com.vm.eea.ui.project.updateProjectMaxvoltDrop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.VoltDropInput

@Composable
fun UpdateProjectMaxVoltDropScreen(viewModel: UpdateProjectMaxVoltDropViewModel){
    val state by viewModel.container.stateFlow.collectAsState()
    val keyboardOptions= if(state.canExecute){
        KeyboardOptions(imeAction = ImeAction.Done)
    }else{
        KeyboardOptions(imeAction = ImeAction.None)
    }
    val keyboardActions= if(state.canExecute){
        KeyboardActions (onDone = {viewModel.onSubmit()})
    }else{
        KeyboardActions.Default
    }
    FullPageDialog(pageTitle = state.pageTitle,
        canSubmit = state.canExecute,
        onSubmit = { viewModel.onSubmit()}) {
        Column(modifier = Modifier.fillMaxSize()
            .padding(16.dp),
            ) {


                VoltDropInput("Voltage drop",value = state.value,
                    error = state.error,
                    keyboardActions = keyboardActions,
                    keyboardOptions = keyboardOptions) { value ->
                    viewModel.onChange(value)
                }




        }
    }
}


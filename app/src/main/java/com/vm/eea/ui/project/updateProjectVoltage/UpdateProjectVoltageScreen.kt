package com.vm.eea.ui.project.updateProjectVoltage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.GridSelector
import com.vm.eea.ui.components.VoltageInput


@Composable
fun UpdateProjectVoltageScreen(viewModel: UpdateProjectVoltageViewModel){
  val state by viewModel.container.stateFlow.collectAsState()


        val keyboardOptions= if(state.canExecute){
            KeyboardOptions(imeAction = ImeAction.Done)
        }else{
            KeyboardOptions(imeAction = ImeAction.None)
        }
        val keyboardActions= if(state.canExecute){
            KeyboardActions (onDone = {viewModel.submit(true)})
        }else{
            KeyboardActions.Default
        }
        FullPageDialog(pageTitle = state.pageTitle,
            canSubmit = state.canExecute,
            onSubmit = { viewModel.submit(true)}) {
            Column(modifier = Modifier.fillMaxSize()
                .padding(16.dp)) {


                    VoltageInput("voltage",value = state.value,
                        unit = state.unit,
                        error = state.error,
                        keyboardActions = keyboardActions,
                        keyboardOptions = keyboardOptions) { value, unit ->
                        viewModel.onChange(value, unit)
                    }


                GridSelector(modifier = Modifier.padding(top=16.dp),items = state.defaults,
                    onSelect = {viewModel.onDefaultSelect(it)})

            }
        }



}




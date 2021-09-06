package com.vm.eea.ui.panel.updatePanelFeedTemperature

import androidx.compose.foundation.layout.Column
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
import com.vm.eea.ui.components.TemperatureInput


@Composable
fun UpdatePanelFeedTemperatureScreen(viewModel: UpdatePanelFeedTemperatureViewModel) {
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
        Column(modifier = Modifier
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Surface {

                    TemperatureInput("Temperature",value = state.value,
                        modifier = Modifier.padding(top=8.dp,start = 16.dp,bottom = 16.dp,end = 16.dp),
                        unit = state.unit,
                        error = state.error,
                        keyboardActions = keyboardActions,
                        keyboardOptions = keyboardOptions) { value, unit ->
                        viewModel.onValueChange(value, unit)
                    }

            }

            GridSelector(modifier = Modifier.padding(top = 16.dp),items = state.defaults, onSelect = { viewModel.onItemSelect(it) })

        }
    }
}


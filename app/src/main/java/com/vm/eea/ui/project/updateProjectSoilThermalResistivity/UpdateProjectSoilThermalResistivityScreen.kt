package com.vm.eea.ui.project.updateProjectSoilThermalResistivity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.GridSelector
import com.vm.eea.ui.components.ThermalResistivityInput


@Composable
fun UpdateProjectSoilThermalResistivityScreen(viewModel: UpdateProjectSoilThermalResistivityViewModel){
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
    FullPageDialog(pageTitle = "Soil Thermal resistivity",
        canSubmit = state.canExecute,
        onSubmit = { viewModel.submit(true)}) {
        Column(modifier = Modifier
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Surface {

                ThermalResistivityInput("Temperature",value = state.value,
                    modifier = Modifier.padding(top=8.dp,start = 16.dp,bottom = 16.dp,end = 16.dp),
                    unit = state.unit,
                    error = state.error,
                    keyboardActions = keyboardActions,
                    keyboardOptions = keyboardOptions) { value, unit ->
                    viewModel.onChange(value, unit)
                }

            }

            GridSelector(modifier = Modifier.padding(top = 16.dp),items = state.defaults, onSelect = { viewModel.onDefaultSelect(it) })

        }
    }
}


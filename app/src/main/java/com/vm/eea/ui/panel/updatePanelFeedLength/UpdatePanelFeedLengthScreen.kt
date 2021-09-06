package com.vm.eea.ui.panel.updatePanelFeedLength

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.LengthInput


@Composable
fun UpdatePanelFeedLengthScreen(viewModel: UpdatePanelFeedLengthViewModel) {
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
    FullPageDialog(pageTitle = "Feed length",
        canSubmit = state.canExecute,
        onSubmit = { viewModel.onSubmit()}) {
        Column(modifier = Modifier
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card {

                    LengthInput("Feed Length",value = state.value,
                        modifier = Modifier.padding(top=8.dp,start = 16.dp,bottom = 8.dp,end = 16.dp),
                        unit = state.unit,
                        error = state.error,
                        keyboardActions = keyboardActions,
                        keyboardOptions = keyboardOptions) { value, unit ->
                        viewModel.onLengthChange(value, unit)
                    }

            }

        }
    }
}

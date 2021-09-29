package com.vm.eea.ui.panel.addPanle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.*
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AddPanelScreen(viewModel: AddPanelViewModel ) {
    val state by viewModel.container.stateFlow.collectAsState()
    val effect by viewModel.container.sideEffectFlow.collectAsState(initial = Effect.None)
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(effect) {
        launch {
            when(effect){
                Effect.None -> {
                    bottomState.hide()
                }
                is Effect.ShowModal -> {
                    scope.launch {
                        bottomState.show()
                    }
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            BottomSheet("Feeder", modifier = Modifier.fillMaxWidth()) {
                ColSelector(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp,
                    start = 8.dp,
                    end = 8.dp),
                    render = { Text(text = it.code) },
                    items = state.feeders,
                    onSelect = {
                        viewModel.onChangeFeeder(it)
                        scope.launch {
                            bottomState.hide()
                        }
                    })
            }
        }
    ) {
        FullPageDialog(pageTitle = "New Panel", canSubmit = state.canSubmit,
            onSubmit = { viewModel.onSubmit() }) {
            Column(modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(16.dp)) {

                StringInput(label = "Code",
                    value = state.code,
                    error = state.codeError,
                    onChange = { viewModel.onCodeChange(it) })
                StringInput(label = "Description",
                    value = state.description,
                    error = null,
                    onChange = { viewModel.onDescriptionChange(it) })
                DemandFactorInput(label = "Demand factor",
                    value = state.demandFactor.value,
                    error = state.demandFactor.error,
                    onChange = {viewModel.onDemandFactorChange(it)})
                FormItemSelector(modifier = Modifier
                    .fillMaxWidth(),
                    name = "Feeder", value = state.feeder?.code ?: "chose"){ viewModel.onFeederSelectRequest() }

                LengthInput(label = "Feed length",
                    value = state.length,
                    unit = state.lengthUnit,
                    error = state.lengthError,
                    onChange = { s, u -> viewModel.onLengthChange(s, u) })



            }
        }
    }
}



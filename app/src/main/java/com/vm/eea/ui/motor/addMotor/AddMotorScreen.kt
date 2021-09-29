package com.vm.eea.ui.motor.addMotor


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.imePadding
import com.vm.eea.ui.components.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun AddMotorScreen(viewModel: AddMotorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scrollState = rememberScrollState()
    val scope= rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collect {
                scope.launch {
                    focusManager.clearFocus()
                    bottomState.show()
                }

            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            when(state.selector){
                UiState.Selector.Feeder ->  BottomSheet("Feeder",modifier = Modifier.fillMaxWidth()) {
                        ColSelector(modifier = Modifier.padding(top = 8.dp,bottom=16.dp,
                            start = 8.dp,
                            end = 8.dp),
                            render = { Text(text = it.code) },
                            items = state.feeders,
                            onSelect = {
                                viewModel.onFeederSelect(it)
                                scope.launch {
                                    bottomState.hide()
                                }
                            })
                    }
                UiState.Selector.System -> BottomSheet("Current Type",modifier = Modifier.fillMaxWidth()){
                        ColSelector(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp,
                            start = 8.dp,
                            end = 8.dp),
                            render = { Text(text = it()) },
                            items = state.systems,
                            onSelect = {
                                viewModel.onSystemSelect(it)
                                scope.launch {
                                    bottomState.hide()
                                }
                            })

                }
                UiState.Selector.None -> {Text(text = "")}
                UiState.Selector.StartMode -> BottomSheet("Start Mode",modifier = Modifier.fillMaxWidth()){
                    ColSelector(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp,
                        start = 8.dp,
                        end = 8.dp),
                        render = { Text(text = it.name) },
                        items = state.startModes,
                        onSelect = {
                            viewModel.onStartModeSelect(it)
                            scope.launch {
                                bottomState.hide()
                            }
                        })

                }
            }

        }
    ) {
        FullPageDialog(pageTitle = "Add Motor",onSubmit = {viewModel.submit()},canSubmit = state.canSubmit) {
                Column(modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(scrollState)
                    .fillMaxSize().imePadding()
                    ) {

                    StringInput(label = "Code",field = state.code, onChange ={ viewModel.onCodeChange(it) })
                    FormItemSelector(name = "Current type",value = state.system()){ viewModel.onSystemSelectRequest() }
                    PowerInput(label = "Power",field = state.power, onChange =  { s, u -> viewModel.onPowerChange(s, u) })
                    FormItemSelector(name = "Start Mode",value = state.startMode.name){ viewModel.onSTartModeSelectRequest() }
                    FormItemSelector(name = "Feeder",value = state.feeder?.code?:"chose"){ viewModel.onFeederSelectRequest() }
                    LengthInput(label = "Line Length",state.feedLength, onChange = { s,u->viewModel.onLengthChange(s,u) } )
                    PowerfactorSliderInput("Cos\uD835\uDF19 (${state.powerfactor.toFormatString()})",value = state.powerfactor, onChange = {viewModel.onPowerfactorChange(it)})
                    EfficiencySliderInput(title = "Efficiency (${state.efficiency.toFormatString()})",value = state.efficiency,onChange = {viewModel.onEfficiencyChange(it)})


                }

            }


        }

}




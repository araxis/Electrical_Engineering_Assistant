package com.vm.eea.ui.motor.addMotor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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
import com.vm.eea.domain.PowerSystem
import com.vm.eea.ui.*
import com.vm.eea.ui.components.*
import com.vm.eea.ui.models.SimplePanel
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
    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collect {
                scope.launch {
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
                        RowSelector(modifier = Modifier.padding(top = 8.dp,bottom=16.dp,
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
                        GridSelector2(cols = 3,modifier = Modifier.padding(top = 8.dp, bottom = 16.dp,
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
                UiState.Selector.None -> {
                    Text(text = "")
                }
            }

        }
    ) {
        FullPageDialog(pageTitle = "Add Motor",onSubmit = {viewModel.submit()},canSubmit = state.canSubmit) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)) {
                     AddMotorForm(modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                         code = state.code,
                         power = state.power,
                         powerfactor = state.powerfactor,
                         efficiency = state.efficiency,
                         length = state.feedLength,
                         feeder=state.feeder,
                         system=state.system,
                         demandFactor = state.demandFactor,
                         onCodeChange = { viewModel.onCodeChange(it) },
                         onPowerChange = { s, u -> viewModel.onPowerChange(s, u) },
                         onPowerfactorChange = { viewModel.onPowerfactorChange(it) },
                         onEfficiencyChange = { viewModel.onEfficiencyChange(it) },
                         onDemandFactorChange = { viewModel.onDemandFactorChange(it) },
                         onFeederSelectRequest = {viewModel.onFeederSelectRequest()},
                         onSystemSelectRequest={viewModel.onSystemSelectRequest()},
                         onLengthChange = {s,u->viewModel.onLengthChange(s,u)}
                     )
                }

            }

        }

}

@Composable
fun AddMotorForm(
    code:StringField,
    power: PowerField,
    powerfactor: PowerfactorField,
    efficiency: EfficiencyField,
    length: LengthField,
    feeder: SimplePanel?,
    system: PowerSystem,
    demandFactor: PowerfactorField,
    onCodeChange: StringListener,
    onPowerChange: PowerListener,
    onLengthChange:LengthListener,
    onPowerfactorChange: PowerfactorListener,
    onEfficiencyChange: EfficiencyListener,
    onDemandFactorChange: PowerfactorListener,
    onFeederSelectRequest:()->Unit,
    onSystemSelectRequest:()->Unit,
    modifier:Modifier=Modifier
){
    Column (modifier = modifier) {
        StringInput(label = "Code", value = code.value, error = code.error, onChange = onCodeChange)
        FormItemSelector(modifier= Modifier.fillMaxWidth().clickable { onSystemSelectRequest() },name = "Current type",value = system())
        PowerInput(modifier = Modifier,label = "Power", value = power.value,unit = power.second, error = power.error, onChange = onPowerChange)
        PowerFactorInput(modifier = Modifier,label = "powerfactor",value = powerfactor.value,error = powerfactor.error,onChange = onPowerfactorChange)
        EfficiencyInput(modifier = Modifier,label = "Efficiency", value = efficiency.value, error =efficiency.error , onChange =onEfficiencyChange )
        PowerFactorInput(modifier = Modifier,label = "Demand factor", value =demandFactor.value , error =demandFactor.error , onChange =onDemandFactorChange )
        LengthInput(label = "Line Length", value = length.value, unit =length.second , error = length.error, onChange =onLengthChange )
        FormItemSelector(modifier= Modifier.fillMaxWidth().clickable { onFeederSelectRequest() },name = "Feeder",value = feeder?.code?:"chose")
    }

}
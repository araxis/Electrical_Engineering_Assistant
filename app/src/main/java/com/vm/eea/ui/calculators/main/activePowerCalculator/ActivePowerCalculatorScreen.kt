package com.vm.eea.ui.calculators.main.activePowerCalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vm.eea.application.Power
import com.vm.eea.application.SelectableItem
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.activePowerCalculator.apparentCosPhi.ApparentPowerCosPhiActivePowerViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.apparentCosPhi.ApparentPowerCosPhiActivePowerScreen
import com.vm.eea.ui.calculators.main.activePowerCalculator.currentImpedance.CurrentImpedanceActivePowerViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.currentImpedance.CurrentImpedanceScreen
import com.vm.eea.ui.calculators.main.activePowerCalculator.currentResistance.CurrentResistanceActivePowerCalculatorViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.currentResistance.CurrentResistanceScreen
import com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveApparent.ReactiveApparentActivePowerViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveApparent.ReactiveApparentScreen
import com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveCosPhi.ReactiveCosPhiScreen
import com.vm.eea.ui.calculators.main.activePowerCalculator.reactiveCosPhi.ReactivePowerCosPhiActivePowerViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.voltageCurrent.VoltageCurrentPowerCalculatorScreen
import com.vm.eea.ui.calculators.main.activePowerCalculator.voltageCurrent.VoltageCurrentPowerCalculatorViewModel
import com.vm.eea.ui.calculators.main.activePowerCalculator.voltageImpedance.VoltageImpedanceActivePowerCalculatorScreen
import com.vm.eea.ui.calculators.main.activePowerCalculator.voltageImpedance.VoltageImpedanceActivePowerCalculatorViewModel
import com.vm.eea.ui.components.BottomSheet
import com.vm.eea.ui.components.ColSelector
import com.vm.eea.ui.components.Page1
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun ActivePowerCalculatorScreen(viewModel: ActivePowerCalculatorViewModel){
    val state by viewModel.container.stateFlow.collectAsState()
    val scope= rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collect {
                scope.launch {
                    focusManager.clearFocus()
                    bottomState.animateTo(ModalBottomSheetValue.Expanded)
                }

            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {

             BottomSheet("Inputs",modifier = Modifier.fillMaxHeight()){
                    ColSelector(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp,
                        start = 8.dp,
                        end = 8.dp),
                        render = { Text(text = it.description) },
                        items = state.calculators,
                        onSelect = {
                            viewModel.onInputTypeChange(SelectableItem(it,false))
                            scope.launch {
                                bottomState.hide()
                            }
                        })

                }



        }
    ) {
        Page1(pageTitle = "Active input") {
            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()) {

                Render(state.currentInput, viewModel)
            }
        }
    }
}



@Composable
fun Render(inputType: ActivePowerInputType, parentViewModel: ActivePowerCalculatorViewModel) {
   when(inputType){
       ActivePowerInputType.ApparentCosPhi -> {
           val viewModel = getViewModel<ApparentPowerCosPhiActivePowerViewModel>()
           ApparentPowerCosPhiActivePowerScreen(viewModel = viewModel) {
               parentViewModel.onInputChangeRequestSelect()}
       }
       ActivePowerInputType.CurrentImpedance -> {
           val viewModel = getViewModel<CurrentImpedanceActivePowerViewModel>()
           CurrentImpedanceScreen(viewModel = viewModel) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
       ActivePowerInputType.CurrentResistance -> {
           val viewModel = getViewModel<CurrentResistanceActivePowerCalculatorViewModel>()
           CurrentResistanceScreen(viewModel = viewModel) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
       ActivePowerInputType.ReactiveApparent ->{
           val viewModel = getViewModel<ReactiveApparentActivePowerViewModel>()
           ReactiveApparentScreen(viewModel = viewModel) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
       ActivePowerInputType.ReactiveCosPhi -> {
           val viewModel = getViewModel<ReactivePowerCosPhiActivePowerViewModel>()
           ReactiveCosPhiScreen(viewModel = viewModel) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
       ActivePowerInputType.VoltageCurrent -> {
           val viewModel = getViewModel<VoltageCurrentPowerCalculatorViewModel>()
           VoltageCurrentPowerCalculatorScreen(viewModel = viewModel) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
       ActivePowerInputType.VoltageImpedance -> {
           val viewModel = getViewModel<VoltageImpedanceActivePowerCalculatorViewModel>()
           VoltageImpedanceActivePowerCalculatorScreen(viewModel = viewModel) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
   }
}

@Composable
fun DisplayActivePowerResult(state: FormState<Power>) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        when(state){
            is FormState.Calculating -> Text(text = state.value,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
            is FormState.Failed -> Text(text = state.error,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
            is FormState.Ready -> Text(text = state.value.toFormatString(),modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        }
    }

}

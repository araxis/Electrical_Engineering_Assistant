package com.vm.eea.ui.calculators.main.apparentPowerCalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vm.eea.application.ApparentPower
import com.vm.eea.application.SelectableItem
import com.vm.eea.ui.calculators.FormState
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.activeReactive.ReactiveActiveApparentCalculatorScreen
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.activeReactive.ReactiveActivePowerApparentCalculatorViewModel
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.powerCosPhi.ActivePowerCosPhiApparentPowerViewModel
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.powerCosPhi.ActivePowerCosPhiScreen
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.reactiveCosPhi.ReactiveCosPhiApparentPowerScreen
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.reactiveCosPhi.ReactiveCosPhiApparentPowerViewModel
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.voltageCurrent.VoltageCurrentApparentCalculatorScreen
import com.vm.eea.ui.calculators.main.apparentPowerCalculator.voltageCurrent.VoltageCurrentApparentCalculatorViewModel
import com.vm.eea.ui.components.BottomSheet
import com.vm.eea.ui.components.ColSelector
import com.vm.eea.ui.components.Page1
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun ApparentPowerCalculatorScreen(viewModel: ApparentPowerCalculatorViewModel){
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
        Page1(pageTitle = "Apparent input") {
            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()) {

                Render(state.currentInput, viewModel)
            }
        }
    }
}



@Composable
fun Render(inputType: ApparentPowerInputType, parentViewModel: ApparentPowerCalculatorViewModel) {
   when(inputType){
       ApparentPowerInputType.VoltageCurrent -> {
           val vm= getViewModel<VoltageCurrentApparentCalculatorViewModel>()
           VoltageCurrentApparentCalculatorScreen(viewModel = vm) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
       ApparentPowerInputType.PowerCosPhi -> {
           val vm= getViewModel<ActivePowerCosPhiApparentPowerViewModel>()
           ActivePowerCosPhiScreen(viewModel = vm) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
       ApparentPowerInputType.ReactivePowerCosPhi -> {
           val vm= getViewModel<ReactiveCosPhiApparentPowerViewModel>()
           ReactiveCosPhiApparentPowerScreen(viewModel = vm ) {
               parentViewModel.onInputChangeRequestSelect()
           }
       }
       ApparentPowerInputType.ActiveReactive -> {
           val vm= getViewModel<ReactiveActivePowerApparentCalculatorViewModel>()
           ReactiveActiveApparentCalculatorScreen(vm){
               parentViewModel.onInputChangeRequestSelect()
           }
       }
   }
}

@Composable
fun DisplayApparentPowerResult(state: FormState<ApparentPower>) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        when(state){
            is FormState.Calculating -> Text(text = state.value,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
            is FormState.Failed -> Text(text = state.error,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
            is FormState.Ready -> Text(text = state.value.toFormatString(),modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        }
    }

}

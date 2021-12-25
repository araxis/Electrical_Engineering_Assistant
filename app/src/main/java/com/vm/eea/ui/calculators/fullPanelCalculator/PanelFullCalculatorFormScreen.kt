package com.vm.eea.ui.calculators.fullPanelCalculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.*
import com.vm.eea.R
import com.vm.eea.application.calculators.applicationProject.panelProject.PanelMotor
import com.vm.eea.utilities.displayOrZero
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun PanelFullCalculatorFormScreen(viewModel: PanelFullCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    val effect by viewModel.container.sideEffectFlow.collectAsState(initial =Effect.HideMenu())
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope= rememberCoroutineScope()
    LaunchedEffect(effect) {
        launch {

                scope.launch {
                    when(effect){
                        is Effect.HideMenu -> bottomState.hide()
                        is Effect.ShowMotorMenu -> bottomState.show()
                    }


            }
        }
    }
    val fab=@Composable{

        ExtendedFloatingActionButton(
            onClick = { viewModel.onAddMotor() },
            text={ Text(text = "motor")},icon = {
                Icon( Icons.Filled.Add,contentDescription = "Localized description")
            })
    }
  
    ModalBottomSheetLayout(sheetState=bottomState,sheetContent ={
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            item { ActionItem(iconRes = R.drawable.trash,"Remove"){
                scope.launch {
                    bottomState.hide()
                    viewModel.removeMotor(state.currentMotorId)
                }
            } }


            item { ActionItem(iconRes = R.drawable.copy,"Add copy"){
                scope.launch {
                    bottomState.hide()
                    viewModel.addCopy(state.currentMotorId)
                }

            } }
            item { ActionItem(iconRes = R.drawable.reports,"Report"){
                scope.launch {
                    bottomState.hide()
                    viewModel.showMotorReports(state.currentMotorId)
                }
            }
            }
            item { ActionItem(iconRes = R.drawable.edit,"Edit"){
                scope.launch {
                    bottomState.hide()
                    viewModel.editMotor(state.currentMotorId)
                }

            } }

        }
    } ) {
        Page1(pageTitle = "Panel calculator",
            actions = {
                IconButton(onClick = { viewModel.showPanelReports() },enabled = state.canCalculate) {
                    Icon(painter = painterResource(id = R.drawable.calc), null)
                }
            },
            floatingActionButton= fab,
            floatingActionButtonPosition = FabPosition.Center,
            ) {
            Column(modifier = Modifier.padding(8.dp)) {
                 VoltageInput(label = "Line-Null",
                    field = state.lineNullVoltage,
                    onValueChange = {s,u->viewModel.onLineNullVoltageChange(s,u)})

                 VoltageInput(label = "Line-Line",
                            field = state.lineLineVoltage,
                            onValueChange = {s,u->viewModel.onLineLineVoltageChange(s,u)})

                CosPhiInput(label = "Desired power factor", field = state.demandFactor, onChange ={viewModel.onDemandFactorChange(it)} )
                CoincidenceFactorInput(field = state.coincidenceFactor,onChange = {viewModel.onCoincidenceFactorChange(it)})

                LazyColumn(Modifier.fillMaxSize().padding(top=8.dp)) {
                    if(state.loads.isEmpty()){
                      item{
                          Column(modifier = Modifier.fillParentMaxSize(),
                              verticalArrangement = Arrangement.Center,
                          horizontalAlignment = Alignment.CenterHorizontally) {
                              Text(text = "motor list")}
                          }

                    }else{
                        items(state.loads){
                            PanelMotorItem(it,itemAction = {viewModel.onMotorClicked(it)},
                                menuAction = {viewModel.onMotorMenuClicked(it)})
                        }
                        item {
                            Spacer(modifier = Modifier.height(70.dp))
                        }
                    }

                }
            }

        }
    }


}

@Composable
fun PanelMotorItem(motor:PanelMotor, modifier: Modifier=Modifier, itemAction:()->Unit,menuAction:()->Unit){
    Surface(modifier = modifier) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { itemAction() }

                ,horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = motor.power.displayOrZero(),modifier=Modifier.padding(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = motor.current.displayOrZero(),modifier=Modifier.padding(top = 8.dp,bottom = 8.dp))
                    IconButton(onClick = { menuAction() }) {
                        Icon(painter = painterResource( R.drawable.dot_menu), contentDescription = "")
                    }


                }

            }

    }

}


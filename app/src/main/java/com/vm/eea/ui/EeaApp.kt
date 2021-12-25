package com.vm.eea.ui


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vm.eea.application.toMotorId
import com.vm.eea.application.toPanelId
import com.vm.eea.ui.motor.motorResult.FullMotorCalculationResultViewModel
import com.vm.eea.ui.calculators.fullMotorCalculator.FullMotorCalculatorFormScreen
import com.vm.eea.ui.calculators.fullMotorCalculator.FullMotorCalculatorFormViewModel

import com.vm.eea.ui.calculators.fullPanelCalculator.PanelFullCalculatorFormScreen
import com.vm.eea.ui.calculators.fullPanelCalculator.PanelFullCalculatorViewModel
import com.vm.eea.ui.calculators.motor.circuitBreaker.MotorCircuitBreakerCalculatorScreen
import com.vm.eea.ui.calculators.motor.circuitBreaker.MotorCircuitBreakerCalculatorViewModel
import com.vm.eea.ui.calculators.motor.cosPhi.MotorCosPhiCalculatorScreen
import com.vm.eea.ui.calculators.motor.cosPhi.MotorCosPhiCalculatorViewModel
import com.vm.eea.ui.calculators.motor.current.MotorCurrentCalculatorScreen
import com.vm.eea.ui.calculators.motor.current.MotorCurrentCalculatorViewModel
import com.vm.eea.ui.calculators.motor.efficiency.MotorEfficiencyCalculatorScreen
import com.vm.eea.ui.calculators.motor.efficiency.MotorEfficiencyCalculatorViewModel
import com.vm.eea.ui.calculators.motor.guard.MotorGuardCalculatorScreen
import com.vm.eea.ui.calculators.motor.guard.MotorGuardCalculatorViewModel
import com.vm.eea.ui.calculators.motor.power.MotorActivePowerCalculatorScreen
import com.vm.eea.ui.calculators.motor.power.MotorActivePowerCalculatorViewModel
import com.vm.eea.ui.calculators.motor.slip.MotorSlipCalculatorScreen
import com.vm.eea.ui.calculators.motor.slip.MotorSlipCalculatorViewModel
import com.vm.eea.ui.calculators.motor.speed.MotorSpeedCalculatorScreen
import com.vm.eea.ui.calculators.motor.speed.MotorSpeedCalculatorViewModel
import com.vm.eea.ui.calculators.motor.startMode.MotorStartModeCalculatorScreen
import com.vm.eea.ui.calculators.motor.startMode.MotorStartModeCalculatorViewModel
import com.vm.eea.ui.calculators.motor.torque.MotorTorqueCalculatorScreen
import com.vm.eea.ui.calculators.motor.torque.MotorTorqueCalculatorViewModel
import com.vm.eea.ui.calculators.motor.voltage.MotorVoltageCalculatorScreen
import com.vm.eea.ui.calculators.motor.voltage.MotorVoltageCalculatorViewModel
import com.vm.eea.ui.home.HomeScreen
import com.vm.eea.ui.home.HomeViewModel
import com.vm.eea.ui.motor.addPanelMotor.AddPanelMotorScreen
import com.vm.eea.ui.motor.addPanelMotor.AddPanelMotorViewModel

import com.vm.eea.ui.motor.motorFullupdate.UpdateMotorScreen
import com.vm.eea.ui.motor.motorFullupdate.UpdateMotorViewModel
import com.vm.eea.ui.motor.motorResult.MotorFullResultPage
import com.vm.eea.ui.panel.panelResult.PanelResultScreen
import com.vm.eea.ui.panel.panelResult.PanelResultViewModel



import com.vm.eea.ui.theme.ElectricalEngineeringAssistantTheme
import com.vm.eea.utilities.composable
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EeaApp(navigationManager: NavigationManager){

    val navController = rememberNavController()
                navigationManager.commands.collectAsState().value.also { command ->
                if (command.route.isNotEmpty()) {
                    if(command.route=="back"){
                        navController.popBackStack()
                    }else{
                        navController.navigate(command.route)
                    }

                }
            }
    ElectricalEngineeringAssistantTheme {


                NavHost(navController, Destinations.Home.route) {

                   composable(Destinations.Home){
                       val viewModel= getViewModel<HomeViewModel>()
                       HomeScreen(viewModel = viewModel)
                   }
                   composable(MotorDestinations.UpdateMotor){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel:UpdateMotorViewModel= getViewModel(parameters = { parametersOf(motorId)})
                             UpdateMotorScreen(viewModel)
                         }
                   navigation("motorCalculator","motorCalculators"){
                         composable(MotorCalculatorDestinations.Current){
                             val viewModel= getViewModel<MotorCurrentCalculatorViewModel>()
                             MotorCurrentCalculatorScreen(viewModel)
                         }
                         composable(MotorCalculatorDestinations.Voltage){
                             val viewModel= getViewModel<MotorVoltageCalculatorViewModel>()
                             MotorVoltageCalculatorScreen(viewModel)
                         }
                         composable(MotorCalculatorDestinations.Power){
                             val viewModel= getViewModel<MotorActivePowerCalculatorViewModel>()
                             MotorActivePowerCalculatorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.CosPhi){
                             val viewModel= getViewModel<MotorCosPhiCalculatorViewModel>()
                             MotorCosPhiCalculatorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.Efficiency){
                             val viewModel= getViewModel<MotorEfficiencyCalculatorViewModel>()
                             MotorEfficiencyCalculatorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.Speed){
                             val viewModel= getViewModel<MotorSpeedCalculatorViewModel>()
                             MotorSpeedCalculatorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.Slip){
                             val viewModel= getViewModel<MotorSlipCalculatorViewModel>()
                             MotorSlipCalculatorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.Torque){
                             val viewModel= getViewModel<MotorTorqueCalculatorViewModel>()
                             MotorTorqueCalculatorScreen(viewModel)
                         }
                         composable(MotorCalculatorDestinations.StartMode){
                             val viewModel= getViewModel<MotorStartModeCalculatorViewModel>()
                             MotorStartModeCalculatorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.CircuitBreaker){
                             val viewModel= getViewModel<MotorCircuitBreakerCalculatorViewModel>()
                             MotorCircuitBreakerCalculatorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.Guard){
                             val viewModel= getViewModel<MotorGuardCalculatorViewModel>()
                             MotorGuardCalculatorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.FullMotorForm){
                             val fullMotorCalculatorViewModel= getViewModel<FullMotorCalculatorFormViewModel>()
                             FullMotorCalculatorFormScreen(fullMotorCalculatorViewModel)
                         }

                         composable(MotorCalculatorDestinations.FullMotorResult){
                             val motorId=(it.arguments?.getLong("motorId")).toMotorId()
                             val viewModel= getViewModel<FullMotorCalculationResultViewModel>(parameters= {parametersOf(motorId)})
                             MotorFullResultPage(viewModel)
                         }

                         composable(MotorCalculatorDestinations.FullPanelForm){
                             val viewModel= getViewModel<PanelFullCalculatorViewModel>()
                             PanelFullCalculatorFormScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.AddPanelMotor){
                             val panelId=(it.arguments?.getLong("panelId")).toPanelId()
                             val viewModel= getViewModel<AddPanelMotorViewModel>(parameters= {parametersOf(panelId)})
                             AddPanelMotorScreen(viewModel)
                         }

                         composable(MotorCalculatorDestinations.FullPanelResult){
                             val panelId=(it.arguments?.getLong("panelId")).toPanelId()
                             val viewModel= getViewModel<PanelResultViewModel>(parameters= {parametersOf(panelId)})
                             PanelResultScreen(viewModel)
                         }

                     }


                }




    }
}
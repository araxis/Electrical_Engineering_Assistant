package com.vm.eea.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.InternalTextApi
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.vm.eea.domain.Environment
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.RelationType
import com.vm.eea.domain.project.WireSizeType
import com.vm.eea.ui.motor.addMotor.AddMotorScreen
import com.vm.eea.ui.motor.addMotor.AddMotorViewModel
import com.vm.eea.ui.motor.motorDetails.MotorDetailsScreen
import com.vm.eea.ui.motor.motorDetails.MotorDetailsViewModel
import com.vm.eea.ui.motor.updateMotor.updateDemanFactor.UpdateMotorDemandFactorScreen
import com.vm.eea.ui.motor.updateMotor.updateDemanFactor.UpdateMotorDemandFactorViewMode
import com.vm.eea.ui.motor.updateMotor.updateMotorCode.UpdateMotorCodeScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorCode.UpdateMotorCodeViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorFeeder.UpdateMotorFeederScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorFeeder.UpdateMotorFeederViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPower.UpdateMotorPowerScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorPower.UpdateMotorPowerViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor.UpdateMotorPowerFactorViewMode
import com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor.UpdateMotorPowerfactorScreen
import com.vm.eea.ui.panel.addPanle.AddPanelScreen
import com.vm.eea.ui.panel.addPanle.AddPanelViewModel
import com.vm.eea.ui.panel.panelDetails.PanelDetailsScreen
import com.vm.eea.ui.panel.panelDetails.PanelDetailsViewModel
import com.vm.eea.ui.panel.updateFeeder.UpdateFeederViewModel
import com.vm.eea.ui.panel.updateFeeder.UpdatePanelFeederScreen
import com.vm.eea.ui.panel.updatePanelCode.UpdatePanelCodeScreen
import com.vm.eea.ui.panel.updatePanelCode.UpdatePanelCodeViewModel
import com.vm.eea.ui.panel.updatePanelFeedCircuitCount.UpdatePanelFeedCircuitCountScreen
import com.vm.eea.ui.panel.updatePanelFeedCircuitCount.UpdatePanelFeedCircuitCountViewModel
import com.vm.eea.ui.panel.updatePanelFeedConductor.UpdatePanelFeedConductorScreen
import com.vm.eea.ui.panel.updatePanelFeedConductor.UpdatePanelFeedConductorViewModel
import com.vm.eea.ui.panel.updatePanelFeedInsulation.UpdatePanelFeedInsulationScreen
import com.vm.eea.ui.panel.updatePanelFeedInsulation.UpdatePanelFeedInsulationViewModel
import com.vm.eea.ui.panel.updatePanelFeedLength.UpdatePanelFeedLengthScreen
import com.vm.eea.ui.panel.updatePanelFeedLength.UpdatePanelFeedLengthViewModel
import com.vm.eea.ui.panel.updatePanelFeedMethodOfInstallation.UpdatePanelFeedMethodOfInstallationScreen
import com.vm.eea.ui.panel.updatePanelFeedMethodOfInstallation.UpdatePanelFeedMethodOfInstallationViewModel
import com.vm.eea.ui.panel.updatePanelFeedSoilResistivity.UpdatePanelFeedSoilResistivityScreen
import com.vm.eea.ui.panel.updatePanelFeedSoilResistivity.UpdatePanelFeedSoilResistivityViewModel
import com.vm.eea.ui.panel.updatePanelFeedTemperature.UpdatePanelFeedTemperatureScreen
import com.vm.eea.ui.panel.updatePanelFeedTemperature.UpdatePanelFeedTemperatureViewModel
import com.vm.eea.ui.panel.updatePanelFeedVoltDrop.UpdatePanelFeedVoltDropScreen
import com.vm.eea.ui.panel.updatePanelFeedVoltDrop.UpdatePanelFeedVoltDropViewModel
import com.vm.eea.ui.project.addProject.AddProjectScreen
import com.vm.eea.ui.project.projectCenter.ProjectCenterScreen
import com.vm.eea.ui.project.projectCenter.ProjectCenterViewModel
import com.vm.eea.ui.project.projectDetailsView.ProjectDetailViewModel
import com.vm.eea.ui.project.projectDetailsView.ProjectDetailsScreen
import com.vm.eea.ui.project.projectList.ProjectListScreen
import com.vm.eea.ui.project.updateProjectAltitude.UpdateProjectAltitudeScreen
import com.vm.eea.ui.project.updateProjectAltitude.UpdateProjectAltitudeViewModel
import com.vm.eea.ui.project.updateProjectCode.UpdateProjectCodeScreen
import com.vm.eea.ui.project.updateProjectCode.UpdateProjectCodeViewModel
import com.vm.eea.ui.project.updateProjectConductor.UpdateProjectConductorScreen
import com.vm.eea.ui.project.updateProjectConductor.UpdateProjectConductorViewModel
import com.vm.eea.ui.project.updateProjectCuircuitCount.UpdateProjectCircuitCountScreen
import com.vm.eea.ui.project.updateProjectCuircuitCount.UpdateProjectCircuitCountViewModel
import com.vm.eea.ui.project.updateProjectInsulation.UpdateProjectInsulationScreen
import com.vm.eea.ui.project.updateProjectInsulation.UpdateProjectInsulationViewModel
import com.vm.eea.ui.project.updateProjectMaxvoltDrop.UpdateProjectMaxVoltDropScreen
import com.vm.eea.ui.project.updateProjectMaxvoltDrop.UpdateProjectMaxVoltDropViewModel
import com.vm.eea.ui.project.updateProjectMethodOfInstallation.UpdateProjectMethodOfInstallationScreen
import com.vm.eea.ui.project.updateProjectMethodOfInstallation.UpdateProjectMethodOfInstallationViewModel
import com.vm.eea.ui.project.updateProjectPowerfactor.UpdateProjectPowerfactorScreen
import com.vm.eea.ui.project.updateProjectPowerfactor.UpdateProjectPowerfactorViewModel
import com.vm.eea.ui.project.updateProjectSoilThermalResistivity.UpdateProjectSoilThermalResistivityScreen
import com.vm.eea.ui.project.updateProjectSoilThermalResistivity.UpdateProjectSoilThermalResistivityViewModel
import com.vm.eea.ui.project.updateProjectTemperature.UpdateProjectTemperatureScreen
import com.vm.eea.ui.project.updateProjectTemperature.UpdateProjectTemperatureViewModel
import com.vm.eea.ui.project.updateProjectUnitOfLength.UpdateProjectUnitOfLengthScreen
import com.vm.eea.ui.project.updateProjectUnitOfLength.UpdateProjectUnitOfLengthViewModel
import com.vm.eea.ui.project.updateProjectUnitOfPower.UpdateProjectUnitOfPowerScreen
import com.vm.eea.ui.project.updateProjectUnitOfPower.UpdateProjectUnitOfPowerViewModel
import com.vm.eea.ui.project.updateProjectUnitOfTemperature.UpdateProjectUnitOfTemperatureScreen
import com.vm.eea.ui.project.updateProjectUnitOfTemperature.UpdateProjectUnitOfTemperatureViewModel
import com.vm.eea.ui.project.updateProjectUnitOfWireSize.UpdateProjectUnitOfWireSizeScreen
import com.vm.eea.ui.project.updateProjectUnitOfWireSize.UpdateProjectUnitOfWireSizeViewModel
import com.vm.eea.ui.project.updateProjectVoltage.UpdateProjectVoltageScreen
import com.vm.eea.ui.project.updateProjectVoltage.UpdateProjectVoltageViewModel
import com.vm.eea.ui.project.updateProjectwireSize.UpdateProjectWireSizeScreen
import com.vm.eea.ui.project.updateProjectwireSize.UpdateProjectWireSizeViewModel
import com.vm.eea.ui.theme.ElectricalEngineeringAssistantTheme
import com.vm.eea.utilities.composable
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@InternalTextApi
@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun EeaApp(navigationManager: NavigationManager){
//    val systemUiController = rememberSystemUiController()
//    systemUiController.isStatusBarVisible=false
//    val useDarkIcons = MaterialTheme.colors.isLight
//    val statusBarColor= MaterialTheme.colors.primarySurface

    val navController = rememberNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator
//
//    SideEffect {
//        // Update all of the system bar colors to be transparent, and use
//        // dark icons if we're in light theme
//        systemUiController.setStatusBarColor(
//            color = statusBarColor,
//            darkIcons = useDarkIcons
//        )
//    }
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

        ModalBottomSheetLayout(bottomSheetNavigator,
               sheetBackgroundColor = Color.Transparent,
                sheetElevation = 0.dp,
            ) {
                NavHost(navController, Destinations.ProjectList.route) {

                    composable(Destinations.ProjectList) {
                        ProjectListScreen()
                    }

                    composable(Destinations.ProjectDetails){
                        val projectId=it.arguments?.getLong("projectId")?:-1
                        val viewModel:ProjectDetailViewModel= getViewModel(parameters = { parametersOf(projectId)})
                        ProjectDetailsScreen(viewModel)
                    }
                    composable(Destinations.NewProject){
                        AddProjectScreen()
                    }
                     navigation("settings","project/update"){
                         composable(Destinations.UpdateProjectVoltage){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val system= it.arguments?.get("system") as PowerSystem
                             val viewModel= getViewModel<UpdateProjectVoltageViewModel>(parameters ={ parametersOf(projectId,system)})
                             UpdateProjectVoltageScreen(viewModel)

                         }
                         composable(Destinations.ProjectAltitudeUpdate){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel:UpdateProjectAltitudeViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectAltitudeScreen(viewModel)
                         }
                         composable(Destinations.ProjectTemperatureUpdate){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val environment= it.arguments?.get("environment") as Environment
                             val viewModel: UpdateProjectTemperatureViewModel = getViewModel(parameters = { parametersOf(projectId,environment)})
                             UpdateProjectTemperatureScreen(viewModel)
                         }
                         composable(Destinations.ProjectSoilResistivityUpdate){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectSoilThermalResistivityViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectSoilThermalResistivityScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectPowerFactor){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val system= it.arguments?.get("system") as PowerSystem
                             val viewModel= getViewModel<UpdateProjectPowerfactorViewModel>(parameters ={ parametersOf(projectId,system)})
                             UpdateProjectPowerfactorScreen(viewModel)

                         }
                         composable(Destinations.UpdateProjectMaxVoltDrop){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val relationType= it.arguments?.get("relationType") as RelationType
                             val viewModel= getViewModel<UpdateProjectMaxVoltDropViewModel>(parameters ={ parametersOf(projectId,relationType)})
                             UpdateProjectMaxVoltDropScreen(viewModel)

                         }
                         composable(Destinations.ProjectCircuitCountUpdate){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectCircuitCountViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectCircuitCountScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectWireSize){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val wireSizeType= it.arguments?.get("type") as WireSizeType
                             val viewModel= getViewModel<UpdateProjectWireSizeViewModel>(parameters ={ parametersOf(projectId,wireSizeType)})
                             UpdateProjectWireSizeScreen(viewModel)

                         }
                         composable(Destinations.UpdateProjectUnitOfPower){

                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectUnitOfPowerViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectUnitOfPowerScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectUnitOfWireSize){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectUnitOfWireSizeViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectUnitOfWireSizeScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectConductor){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectConductorViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectConductorScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectMethodOfInstallation){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectMethodOfInstallationViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectMethodOfInstallationScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectUnitOfTemperature){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectUnitOfTemperatureViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectUnitOfTemperatureScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectUnitOfLength){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectUnitOfLengthViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectUnitOfLengthScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectInsulation){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectInsulationViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectInsulationScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectCode){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel: UpdateProjectCodeViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectCodeScreen(viewModel)
                         }
                        composable(Destinations.ProjectCenter){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel:ProjectCenterViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             ProjectCenterScreen(viewModel)
                         }
                     }
                     navigation("panel","panels"){
                        composable(PanelDestinations.AddPanel){
                            val projectId=it.arguments?.getLong("projectId")?:-1
                            val viewModel:AddPanelViewModel= getViewModel(parameters = { parametersOf(projectId)})
                            AddPanelScreen(viewModel)
                        }
                        composable(PanelDestinations.PanelDetails){
                            val panelId=it.arguments?.getLong("panelId")?:-1
                            val viewModel:PanelDetailsViewModel= getViewModel(parameters = { parametersOf(panelId)})
                            PanelDetailsScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelCode){
                            val panelId=it.arguments?.getLong("panelId")?:-1
                            val viewModel:UpdatePanelCodeViewModel= getViewModel(parameters = { parametersOf(panelId)})
                            UpdatePanelCodeScreen(viewModel)
                        }
                        composable(PanelDestinations.UpdatePanelFeeder){
                            val panelId=it.arguments?.getLong("panelId")?:-1
                            val viewModel:UpdateFeederViewModel= getViewModel(parameters = { parametersOf(panelId)})
                            UpdatePanelFeederScreen(viewModel)
                        }
                        composable(PanelDestinations.UpdatePanelFeedLength){
                            val relationId=it.arguments?.getLong("relationId")?:-1
                            val viewModel:UpdatePanelFeedLengthViewModel= getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedLengthScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedMethodOfInstallation){
                            val relationId=it.arguments?.getLong("relationId")?:-1
                            val viewModel:UpdatePanelFeedMethodOfInstallationViewModel= getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedMethodOfInstallationScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedVoltDrop){
                            val relationId=it.arguments?.getLong("relationId")?:-1
                            val viewModel:UpdatePanelFeedVoltDropViewModel= getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedVoltDropScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedTemperature){
                            val relationId=it.arguments?.getLong("relationId")?:-1
                            val environment= it.arguments?.get("environment") as Environment
                            val viewModel: UpdatePanelFeedTemperatureViewModel = getViewModel(parameters = { parametersOf(relationId,environment)})
                            UpdatePanelFeedTemperatureScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedSoilResistivity){
                            val relationId=it.arguments?.getLong("relationId")?:-1
                            val viewModel:UpdatePanelFeedSoilResistivityViewModel= getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedSoilResistivityScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedConductor){
                            val relationId=it.arguments?.getLong("relationId")?:-1
                            val viewModel: UpdatePanelFeedConductorViewModel = getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedConductorScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedInsulation){
                            val relationId=it.arguments?.getLong("relationId")?:-1
                            val viewModel: UpdatePanelFeedInsulationViewModel = getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedInsulationScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedCircuitCount){
                            val relationId=it.arguments?.getLong("relationId")?:-1
                            val viewModel: UpdatePanelFeedCircuitCountViewModel = getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedCircuitCountScreen(viewModel)
                        }
                    }
                     navigation("motor","Motors"){
                         composable(MotorDestinations.AddMotor){
                             val projectId=it.arguments?.getLong("projectId")?:-1
                             val viewModel:AddMotorViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             AddMotorScreen(viewModel)
                         }

                         composable(MotorDestinations.MotorDetails){
                             val projectId=it.arguments?.getLong("motorId")?:-1
                             val viewModel:MotorDetailsViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             MotorDetailsScreen(viewModel)
                         }

                         composable(MotorDestinations.UpdateMotorCode){
                             val projectId=it.arguments?.getLong("motorId")?:-1
                             val viewModel:UpdateMotorCodeViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             UpdateMotorCodeScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorFeeder){
                             val projectId=it.arguments?.getLong("motorId")?:-1
                             val viewModel:UpdateMotorFeederViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             UpdateMotorFeederScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorPower){
                             val projectId=it.arguments?.getLong("motorId")?:-1
                             val viewModel:UpdateMotorPowerViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             UpdateMotorPowerScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorPowerfactor){
                             val projectId=it.arguments?.getLong("motorId")?:-1
                             val viewModel:UpdateMotorPowerFactorViewMode= getViewModel(parameters = { parametersOf(projectId)})
                             UpdateMotorPowerfactorScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorDemandFactor){
                             val projectId=it.arguments?.getLong("motorId")?:-1
                             val viewModel: UpdateMotorDemandFactorViewMode = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateMotorDemandFactorScreen(viewModel)
                         }
                     }




                }


            }

    }
}
package com.vm.eea.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vm.eea.application.*
import com.vm.eea.application.project.RelationType
import com.vm.eea.ui.motor.addMotor.AddMotorScreen
import com.vm.eea.ui.motor.addMotor.AddMotorViewModel
import com.vm.eea.ui.motor.motorDetails.MotorDetailsScreen
import com.vm.eea.ui.motor.motorDetails.MotorDetailsViewModel
import com.vm.eea.ui.motor.updateMotor.updateDemanFactor.UpdateMotorDemandFactorScreen
import com.vm.eea.ui.motor.updateMotor.updateDemanFactor.UpdateMotorDemandFactorViewModel
import com.vm.eea.ui.motor.updateMotor.updateEfficiency.UpdateEfficiencyScreen
import com.vm.eea.ui.motor.updateMotor.updateEfficiency.UpdateEfficiencyViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorCode.UpdateMotorCodeScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorCode.UpdateMotorCodeViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength.UpdateMotorFeedLineLengthScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength.UpdateMotorFeedLineLengthViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorFeeder.UpdateMotorFeederScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorFeeder.UpdateMotorFeederViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPower.UpdateMotorPowerScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorPower.UpdateMotorPowerViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor.UpdateMotorPowerFactorViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor.UpdateMotorPowerfactorScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationTemperature.UpdateMotorRelationTemperatureScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationTemperature.UpdateMotorRelationTemperatureViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationCircuitCount.UpdateMotorRelationCircuitCountScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationCircuitCount.UpdateMotorRelationCircuitCountViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationConductor.UpdateMotorRelationConductorScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationConductor.UpdateMotorRelationConductorViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationInsulation.UpdateMotorRelationInsulationScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationInsulation.UpdateMotorRelationInsulationViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationMaxVoltDrop.UpdateMotorRelationMaxVoltDropScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationMaxVoltDrop.UpdateMotorRelationMaxVoltDropViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationMethodOfInstallation.UpdateMotorFeedMethodOfInstallationScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationMethodOfInstallation.UpdateMotorFeedMethodOfInstallationViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationSoilResistivity.UpdateMotorRelationSoilResistivityScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationSoilResistivity.UpdateMotorRelationThermalResistivityViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorStartMode.UpdateStartModeScreen
import com.vm.eea.ui.motor.updateMotor.updateMotorStartMode.UpdateStartModeViewModel
import com.vm.eea.ui.panel.addPanle.AddPanelScreen
import com.vm.eea.ui.panel.addPanle.AddPanelViewModel
import com.vm.eea.ui.panel.panelDetails.PanelDetailsScreen
import com.vm.eea.ui.panel.panelDetails.PanelDetailsViewModel
import com.vm.eea.ui.panel.updateFeeder.UpdatePanelFeederViewModel
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
import com.vm.eea.ui.project.updateProjectSoilThermalResistivity.UpdateProjectSoilThermalResistivityScreen
import com.vm.eea.ui.project.updateProjectSoilThermalResistivity.UpdateProjectSoilThermalResistivityViewModel
import com.vm.eea.ui.project.updateProjectTemperature.UpdateProjectTemperatureScreen
import com.vm.eea.ui.project.updateProjectTemperature.UpdateProjectTemperatureViewModel
import com.vm.eea.ui.project.updateProjectVoltage.UpdateProjectVoltageScreen
import com.vm.eea.ui.project.updateProjectVoltage.UpdateProjectVoltageViewModel
import com.vm.eea.ui.project.updateProjectwireSize.UpdateProjectWireSizeScreen
import com.vm.eea.ui.project.updateProjectwireSize.UpdateProjectWireSizeViewModel
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


                NavHost(navController, Destinations.ProjectList.route) {

                    composable(Destinations.ProjectList) {
                        ProjectListScreen()
                    }

                    composable(Destinations.ProjectDetails){
                        val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                        val viewModel:ProjectDetailViewModel= getViewModel(parameters = { parametersOf(projectId)})
                        ProjectDetailsScreen(viewModel)
                    }
                    composable(Destinations.NewProject){
                        AddProjectScreen()
                    }
                     navigation("settings","project/update"){
                         composable(Destinations.UpdateProjectVoltage){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val system= it.arguments?.get("system") as PowerSystem
                             val viewModel= getViewModel<UpdateProjectVoltageViewModel>(parameters ={ parametersOf(projectId,system)})
                             UpdateProjectVoltageScreen(viewModel)

                         }
                         composable(Destinations.ProjectAltitudeUpdate){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel:UpdateProjectAltitudeViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectAltitudeScreen(viewModel)
                         }
                         composable(Destinations.ProjectTemperatureUpdate){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val environment= it.arguments?.get("environment") as Environment
                             val viewModel: UpdateProjectTemperatureViewModel = getViewModel(parameters = { parametersOf(projectId,environment)})
                             UpdateProjectTemperatureScreen(viewModel)
                         }
                         composable(Destinations.ProjectSoilResistivityUpdate){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel: UpdateProjectSoilThermalResistivityViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectSoilThermalResistivityScreen(viewModel)
                         }

                         composable(Destinations.UpdateProjectMaxVoltDrop){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val relationType= it.arguments?.get("relationType") as RelationType
                             val viewModel= getViewModel<UpdateProjectMaxVoltDropViewModel>(parameters ={ parametersOf(projectId,relationType)})
                             UpdateProjectMaxVoltDropScreen(viewModel)

                         }
                         composable(Destinations.ProjectCircuitCountUpdate){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel: UpdateProjectCircuitCountViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectCircuitCountScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectWireSize){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val wireSizeType= it.arguments?.get("type") as WireSizeType
                             val viewModel= getViewModel<UpdateProjectWireSizeViewModel>(parameters ={ parametersOf(projectId,wireSizeType)})
                             UpdateProjectWireSizeScreen(viewModel)

                         }


                         composable(Destinations.UpdateProjectConductor){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel: UpdateProjectConductorViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectConductorScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectMethodOfInstallation){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel: UpdateProjectMethodOfInstallationViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectMethodOfInstallationScreen(viewModel)
                         }

                         composable(Destinations.UpdateProjectInsulation){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel: UpdateProjectInsulationViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectInsulationScreen(viewModel)
                         }
                         composable(Destinations.UpdateProjectCode){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel: UpdateProjectCodeViewModel = getViewModel(parameters = { parametersOf(projectId)})
                             UpdateProjectCodeScreen(viewModel)
                         }
                        composable(Destinations.ProjectCenter){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel:ProjectCenterViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             ProjectCenterScreen(viewModel)
                         }
                     }
                     navigation("panel","panels"){
                        composable(PanelDestinations.AddPanel){
                            val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                            val viewModel:AddPanelViewModel= getViewModel(parameters = { parametersOf(projectId)})
                            AddPanelScreen(viewModel)
                        }
                        composable(PanelDestinations.PanelDetails){
                            val panelId=(it.arguments?.getLong("id")?:-1).toPanelId()
                            val viewModel:PanelDetailsViewModel= getViewModel(parameters = { parametersOf(panelId)})
                            PanelDetailsScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelCode){
                            val panelId=(it.arguments?.getLong("id")?:-1).toPanelId()
                            val viewModel:UpdatePanelCodeViewModel= getViewModel(parameters = { parametersOf(panelId)})
                            UpdatePanelCodeScreen(viewModel)
                        }
                        composable(PanelDestinations.UpdatePanelFeeder){
                            val panelId=(it.arguments?.getLong("id")?:-1).toPanelId()
                            val viewModelPanel:UpdatePanelFeederViewModel= getViewModel(parameters = { parametersOf(panelId)})
                            UpdatePanelFeederScreen(viewModelPanel)
                        }
                        composable(PanelDestinations.UpdatePanelFeedLength){
                            val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                            val viewModel:UpdatePanelFeedLengthViewModel= getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedLengthScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedMethodOfInstallation){
                            val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                            val viewModel:UpdatePanelFeedMethodOfInstallationViewModel= getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedMethodOfInstallationScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedVoltDrop){
                            val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                            val viewModel:UpdatePanelFeedVoltDropViewModel= getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedVoltDropScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedTemperature){
                            val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                            val environment= it.arguments?.get("environment") as Environment
                            val viewModel: UpdatePanelFeedTemperatureViewModel = getViewModel(parameters = { parametersOf(relationId,environment)})
                            UpdatePanelFeedTemperatureScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedSoilResistivity){
                            val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                            val viewModel:UpdatePanelFeedSoilResistivityViewModel= getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedSoilResistivityScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedConductor){
                            val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                            val viewModel: UpdatePanelFeedConductorViewModel = getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedConductorScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedInsulation){
                            val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                            val viewModel: UpdatePanelFeedInsulationViewModel = getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedInsulationScreen(viewModel)
                        }

                        composable(PanelDestinations.UpdatePanelFeedCircuitCount){
                            val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                            val viewModel: UpdatePanelFeedCircuitCountViewModel = getViewModel(parameters = { parametersOf(relationId)})
                            UpdatePanelFeedCircuitCountScreen(viewModel)
                        }
                    }
                     navigation("motor","Motors"){
                         composable(MotorDestinations.AddMotor){
                             val projectId=(it.arguments?.getLong("projectId")?:-1).toProjectId()
                             val viewModel:AddMotorViewModel= getViewModel(parameters = { parametersOf(projectId)})
                             AddMotorScreen(viewModel)
                         }

                         composable(MotorDestinations.MotorDetails){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel:MotorDetailsViewModel= getViewModel(parameters = { parametersOf(motorId)})
                             MotorDetailsScreen(viewModel)
                         }

                         composable(MotorDestinations.UpdateMotorCode){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel:UpdateMotorCodeViewModel= getViewModel(parameters = { parametersOf(motorId)})
                             UpdateMotorCodeScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorFeeder){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel:UpdateMotorFeederViewModel= getViewModel(parameters = { parametersOf(motorId)})
                             UpdateMotorFeederScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorPower){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel:UpdateMotorPowerViewModel= getViewModel(parameters = { parametersOf(motorId)})
                             UpdateMotorPowerScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorPowerfactor){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel:UpdateMotorPowerFactorViewModel= getViewModel(parameters = { parametersOf(motorId)})
                             UpdateMotorPowerfactorScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorDemandFactor){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel: UpdateMotorDemandFactorViewModel = getViewModel(parameters = { parametersOf(motorId)})
                             UpdateMotorDemandFactorScreen(viewModel)
                         }

                         composable(MotorDestinations.UpdateMotorEfficiency){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel: UpdateEfficiencyViewModel = getViewModel(parameters = { parametersOf(motorId)})
                             UpdateEfficiencyScreen(viewModel)
                         }

                         composable(MotorDestinations.UpdateMotorStartMode){
                             val motorId=(it.arguments?.getLong("motorId")?:-1).toMotorId()
                             val viewModel: UpdateStartModeViewModel = getViewModel(parameters = { parametersOf(motorId)})
                             UpdateStartModeScreen(viewModel)
                         }

                         composable(MotorDestinations.UpdateMotorFeedLineLength){
                             val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                             val viewModel: UpdateMotorFeedLineLengthViewModel = getViewModel(parameters = { parametersOf(relationId)})
                             UpdateMotorFeedLineLengthScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorRelationMethodOdInstallation){
                             val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                             val viewModel: UpdateMotorFeedMethodOfInstallationViewModel = getViewModel(parameters = { parametersOf(relationId)})
                             UpdateMotorFeedMethodOfInstallationScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorRelationMaxVoltDrop){
                             val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                             val viewModel: UpdateMotorRelationMaxVoltDropViewModel = getViewModel(parameters = { parametersOf(relationId)})
                             UpdateMotorRelationMaxVoltDropScreen(viewModel)
                         }

                         composable(MotorDestinations.UpdateMotorRelationTemperature){
                             val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                             val environment= it.arguments?.get("environment") as Environment
                             val viewModel: UpdateMotorRelationTemperatureViewModel = getViewModel(parameters = { parametersOf(relationId,environment)})
                             UpdateMotorRelationTemperatureScreen(viewModel)
                         }

                         composable(MotorDestinations.UpdateMotorRelationSoilResistivity){
                             val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                             val viewModel: UpdateMotorRelationThermalResistivityViewModel = getViewModel(parameters = { parametersOf(relationId)})
                             UpdateMotorRelationSoilResistivityScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorRelationInsulation){
                             val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                             val viewModel: UpdateMotorRelationInsulationViewModel = getViewModel(parameters = { parametersOf(relationId)})
                             UpdateMotorRelationInsulationScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorRelationConductor){
                             val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                             val viewModel: UpdateMotorRelationConductorViewModel = getViewModel(parameters = { parametersOf(relationId)})
                             UpdateMotorRelationConductorScreen(viewModel)
                         }
                         composable(MotorDestinations.UpdateMotorRelationCircuitCount){
                             val relationId=(it.arguments?.getLong("relationId")?:-1).toRelationId()
                             val viewModel: UpdateMotorRelationCircuitCountViewModel = getViewModel(parameters = { parametersOf(relationId)})
                             UpdateMotorRelationCircuitCountScreen(viewModel)
                         }
                     }

                }


           // }

    }
}
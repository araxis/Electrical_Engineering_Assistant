package com.vm.eea.di


import com.vm.eea.application.calculators.StartModeCalculator
import com.vm.eea.application.panel.addPanel.AddPanel
import com.vm.eea.application.panel.update.UpdatePanelCode
import com.vm.eea.application.panel.update.UpdatePanelFeeder
import com.vm.eea.application.project.IGetProjectSimpleList
import com.vm.eea.application.project.addProject.AddProject
import com.vm.eea.data.project.GetProjectSimpleList
import com.vm.eea.ui.motor.addMotor.AddMotorViewModel
import com.vm.eea.ui.motor.motorDetails.MotorDetailsViewModel
import com.vm.eea.ui.motor.updateMotor.updateEfficiency.UpdateEfficiencyViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorCode.UpdateMotorCodeViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength.UpdateMotorFeedLineLengthViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorFeeder.UpdateMotorFeederViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPower.UpdateMotorPowerViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor.UpdateMotorPowerFactorViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationCircuitCount.UpdateMotorRelationCircuitCountViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationConductor.UpdateMotorRelationConductorViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationInsulation.UpdateMotorRelationInsulationViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationMaxVoltDrop.UpdateMotorRelationMaxVoltDropViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationMethodOfInstallation.UpdateMotorFeedMethodOfInstallationViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationSoilResistivity.UpdateMotorRelationThermalResistivityViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorRelationTemperature.UpdateMotorRelationTemperatureViewModel
import com.vm.eea.ui.panel.addPanle.AddPanelViewModel
import com.vm.eea.ui.panel.panelDetails.PanelDetailsViewModel
import com.vm.eea.ui.panel.updateFeeder.UpdatePanelFeederViewModel
import com.vm.eea.ui.panel.updatePanelCode.UpdatePanelCodeViewModel
import com.vm.eea.ui.panel.updatePanelFeedCircuitCount.UpdatePanelFeedCircuitCountViewModel
import com.vm.eea.ui.panel.updatePanelFeedConductor.UpdatePanelFeedConductorViewModel
import com.vm.eea.ui.panel.updatePanelFeedInsulation.UpdatePanelFeedInsulationViewModel
import com.vm.eea.ui.panel.updatePanelFeedLength.UpdatePanelFeedLengthViewModel
import com.vm.eea.ui.panel.updatePanelFeedMethodOfInstallation.UpdatePanelFeedMethodOfInstallationViewModel
import com.vm.eea.ui.panel.updatePanelFeedSoilResistivity.UpdatePanelFeedSoilResistivityViewModel
import com.vm.eea.ui.panel.updatePanelFeedTemperature.UpdatePanelFeedTemperatureViewModel
import com.vm.eea.ui.panel.updatePanelFeedVoltDrop.UpdatePanelFeedVoltDropViewModel
import com.vm.eea.ui.project.addProject.AddProjectViewModel
import com.vm.eea.ui.project.projectCenter.ProjectCenterViewModel
import com.vm.eea.ui.project.projectDetailsView.ProjectDetailViewModel
import com.vm.eea.ui.project.projectList.ProjectListViewModel
import com.vm.eea.ui.project.updateProjectAltitude.UpdateProjectAltitudeViewModel
import com.vm.eea.ui.project.updateProjectCode.UpdateProjectCodeViewModel
import com.vm.eea.ui.project.updateProjectConductor.UpdateProjectConductorViewModel
import com.vm.eea.ui.project.updateProjectCuircuitCount.UpdateProjectCircuitCountViewModel
import com.vm.eea.ui.project.updateProjectInsulation.UpdateProjectInsulationViewModel
import com.vm.eea.ui.project.updateProjectMaxvoltDrop.UpdateProjectMaxVoltDropViewModel
import com.vm.eea.ui.project.updateProjectMethodOfInstallation.UpdateProjectMethodOfInstallationViewModel
import com.vm.eea.ui.project.updateProjectSoilThermalResistivity.UpdateProjectSoilThermalResistivityViewModel
import com.vm.eea.ui.project.updateProjectTemperature.UpdateProjectTemperatureViewModel
import com.vm.eea.ui.project.updateProjectVoltage.UpdateProjectVoltageViewModel
import com.vm.eea.ui.project.updateProjectwireSize.UpdateProjectWireSizeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels= module {
  factory<IGetProjectSimpleList> { GetProjectSimpleList(get()) }
  factory { AddProject(get(),get(),get(),get(),get()) }


  factory { AddPanel(get(),get(),get(),get(),get()) }

  factory { UpdatePanelCode(get(),get()) }
  factory { UpdatePanelFeeder(get(),get(),get(),get(),get()) }
  factory { StartModeCalculator() }
  viewModel { ProjectListViewModel(get(),get()) }
  viewModel { params-> UpdateProjectVoltageViewModel(projectId = params.get(),system = params.get(),get(),get(),get()) }
  viewModel { params->ProjectDetailViewModel(projectId = params.get(),get(),get()) }
  viewModel { params->UpdateProjectAltitudeViewModel(projectId = params.get(),get(),get(),get())}
  viewModel { params-> UpdateProjectTemperatureViewModel(projectId = params.get(),environment = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectSoilThermalResistivityViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectMaxVoltDropViewModel(projectId = params.get(),relationType = params.get(),get(),get()) }
  viewModel { params-> UpdateProjectCircuitCountViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params->UpdateProjectWireSizeViewModel(projectId = params.get(),sizingType = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectConductorViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectMethodOfInstallationViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectInsulationViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectCodeViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { AddProjectViewModel(get(),get()) }
  viewModel { params-> AddPanelViewModel(params.get(),get(),get(),get()) }
  viewModel { params-> ProjectCenterViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params->PanelDetailsViewModel(panelId = params.get(),get(),get()) }
  viewModel { params-> UpdatePanelCodeViewModel(panelId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeederViewModel(panelId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedLengthViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedMethodOfInstallationViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedVoltDropViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedTemperatureViewModel(relationId = params.get(),environment = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedSoilResistivityViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedConductorViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedInsulationViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedCircuitCountViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> AddMotorViewModel(projectId = params.get(),get(),get(),get(),get()) }

  viewModel { params-> MotorDetailsViewModel(motorId = params.get(),get(),get()) }
  viewModel { params-> UpdateMotorCodeViewModel(motorId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorFeederViewModel(motorId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorPowerViewModel(motorId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateEfficiencyViewModel(motorId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorFeedLineLengthViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorFeedMethodOfInstallationViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorRelationMaxVoltDropViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorRelationTemperatureViewModel(relationId = params.get(),environment = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorRelationThermalResistivityViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorRelationInsulationViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorRelationConductorViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorRelationCircuitCountViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorPowerFactorViewModel(motorId = params.get(),get(),get(),get()) }

}
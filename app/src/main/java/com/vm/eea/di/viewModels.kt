package com.vm.eea.di


import com.vm.eea.domain.GetDefaultCircuitCounts
import com.vm.eea.domain.defaultAltitude.AddNewDefaultAltitude
import com.vm.eea.domain.defaultAltitude.GetDefaultAltitudes
import com.vm.eea.domain.defaultGroundTemperature.AddNewDefaultTemperature
import com.vm.eea.domain.defaultGroundTemperature.GetDefaultTemperatures
import com.vm.eea.domain.defaultPowerfactor.AddNewDefaultPowerfactor
import com.vm.eea.domain.defaultPowerfactor.GetDefaultPowerFactors
import com.vm.eea.domain.defaultSiolResistivity.AddNewDefaultSoilResistivity
import com.vm.eea.domain.defaultSiolResistivity.GetDefaultSoilResistivity
import com.vm.eea.domain.defaultVoltage.AddNewDefaultVoltage
import com.vm.eea.domain.defaultVoltage.GetDefaultVoltages
import com.vm.eea.domain.defaultWireSize.AddNewDefaultWireSize
import com.vm.eea.domain.defaultWireSize.GetDefaultWireSizes
import com.vm.eea.domain.load.*
import com.vm.eea.domain.panel.*
import com.vm.eea.domain.panelToPanelRelation.GetFeedingRelationByConsumer
import com.vm.eea.domain.panelToPanelRelation.GetFeedingRelationByRelation
import com.vm.eea.domain.panelToPanelRelation.UpdatePanelFeed
import com.vm.eea.domain.project.*
import com.vm.eea.ui.models.GetSimpleMotor
import com.vm.eea.ui.models.GetSimpleMotorList
import com.vm.eea.ui.models.GetSimplePanels
import com.vm.eea.ui.motor.addMotor.AddMotorViewModel
import com.vm.eea.ui.motor.motorDetails.GetMotorDetails
import com.vm.eea.ui.motor.motorDetails.MotorDetailsViewModel
import com.vm.eea.ui.motor.updateMotor.GetMotor
import com.vm.eea.ui.motor.updateMotor.GetMotors
import com.vm.eea.ui.motor.updateMotor.updateDemanFactor.GetMotorDemandFactor
import com.vm.eea.ui.motor.updateMotor.updateDemanFactor.UpdateMotorDemandFactorViewMode
import com.vm.eea.ui.motor.updateMotor.updateMotorCode.UpdateMotorCodeViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorFeeder.GetMotorFeederDetails
import com.vm.eea.ui.motor.updateMotor.updateMotorFeeder.UpdateMotorFeederViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPower.GetMotorPowerDetails
import com.vm.eea.ui.motor.updateMotor.updateMotorPower.UpdateMotorPowerViewModel
import com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor.GetMotorPowerfactorDetail
import com.vm.eea.ui.panel.addPanle.AddPanelViewModel
import com.vm.eea.ui.panel.panelDetails.PanelDetailsViewModel
import com.vm.eea.ui.panel.updateFeeder.UpdateFeederViewModel
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
import com.vm.eea.ui.project.updateProjectPowerfactor.UpdateProjectPowerfactorViewModel
import com.vm.eea.ui.project.updateProjectSoilThermalResistivity.UpdateProjectSoilThermalResistivityViewModel
import com.vm.eea.ui.project.updateProjectTemperature.UpdateProjectTemperatureViewModel
import com.vm.eea.ui.project.updateProjectUnitOfLength.UpdateProjectUnitOfLengthViewModel
import com.vm.eea.ui.project.updateProjectUnitOfPower.UpdateProjectUnitOfPowerViewModel
import com.vm.eea.ui.project.updateProjectUnitOfTemperature.UpdateProjectUnitOfTemperatureViewModel
import com.vm.eea.ui.project.updateProjectUnitOfWireSize.UpdateProjectUnitOfWireSizeViewModel
import com.vm.eea.ui.project.updateProjectVoltage.UpdateProjectVoltageViewModel
import com.vm.eea.ui.project.updateProjectwireSize.UpdateProjectWireSizeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels= module {
  factory { GetSimpleProjects(get()) }
  factory { AddSimpleProject(get(),get(),get(),get()) }
  factory { AddNewDefaultVoltage(get()) }
  factory { UpdateProjectVoltage(get(),get(),get()) }
  factory { GetDefaultVoltages(get()) }
  factory { GetProject(get()) }
  factory { UpdateProjectAltitude(get(),get(),get()) }
  factory { GetDefaultAltitudes(get()) }
  factory { AddNewDefaultAltitude(get()) }
  factory { UpdateProjectTemperature(get(),get(),get()) }
  factory { GetDefaultTemperatures(get()) }
  factory { AddNewDefaultTemperature(get()) }
  factory { UpdateProjectSoilResistivity(get(),get(),get()) }
  factory { GetDefaultSoilResistivity(get()) }
  factory { AddNewDefaultSoilResistivity(get()) }
  factory { AddNewDefaultPowerfactor(get()) }
  factory { GetDefaultPowerFactors(get()) }
  factory { UpdateProjectPowerfactor(get(),get(),get()) }
  factory { UpdateProjectMaxVoltDrop(get()) }
  factory { UpdateProjectCircuitCount(get()) }
  factory { AddNewDefaultWireSize(get(),get()) }
  factory { GetDefaultWireSizes(get()) }
  factory { UpdateProjectWireSize(get()) }
  factory { UpdateProjectUnitOfPower(get()) }
  factory { UpdateProjectUnitOfWireSize(get()) }
  factory { UpdateProjectConductor(get()) }
  factory { UpdateProjectMethodOfInstallation(get()) }
  factory { UpdateProjectUnitOfTemperature(get()) }
  factory { UpdateProjectUnitOfLength(get()) }
  factory { UpdateProjectInsulation(get()) }
  factory { UpdateProjectCode(get()) }
  factory { AddPanel(get(),get(),get(),get(),get()) }
  factory { GetPanels(get()) }
  factory { GetPanel(get()) }
  factory { GetMotors(get()) }
  factory { AddMdp(get()) }
  factory { GetMdp(get()) }
  factory { GetFeedingRelationByConsumer(get()) }
  factory { UpdatePanelCode(get()) }
  factory { GetAvailableFeeders(get(),get()) }
  factory { GetPanelFeeder(get(),get()) }
  factory { UpdatePanelFeeder(get(),get(),get(),get(),get()) }
  factory { UpdatePanelFeed(get(),get(),get(),get()) }
  factory { GetFeedingRelationByRelation(get()) }
  factory { GetDefaultCircuitCounts() }
  factory { GetSimpleMotorList(get()) }
  factory { GetSimplePanels(get()) }
  factory { AddNewMotor(get(),get(),get(),get(),get(),get()) }
  factory { GetMotorDetails(get(),get(),get(),get()) }
  factory { GetMotor(get()) }
  factory { UpdateMotorCode(get()) }
  factory { GetSimpleMotor(get()) }
  factory { GetMotorFeederDetails(get(),get()) }
  factory { UpdateMotorFeeder(get(),get(),get(),get(),get()) }
  factory { GetMotorPowerDetails(get()) }
  factory { GetMotorPowerfactorDetail(get()) }
  factory { UpdateMotorPower(get()) }
  factory { UpdateMotorPowerfactor(get()) }
  factory { UpdateMotorDemandFactor(get()) }
  factory { GetMotorDemandFactor(get()) }

  viewModel { ProjectListViewModel(get(),get()) }
  viewModel { params-> UpdateProjectVoltageViewModel(projectId = params.get(),system = params.get(),get(),get(),get(),get()) }
  viewModel { params->ProjectDetailViewModel(projectId = params.get(),get(),get()) }
  viewModel { params->UpdateProjectAltitudeViewModel(projectId = params.get(),get(),get(),get(),get())}
  viewModel { params-> UpdateProjectTemperatureViewModel(projectId = params.get(),environment = params.get(),get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectSoilThermalResistivityViewModel(projectId = params.get(),get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectPowerfactorViewModel(projectId = params.get(),system = params.get(),get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectMaxVoltDropViewModel(projectId = params.get(),relationType = params.get(),get(),get()) }
  viewModel { params-> UpdateProjectCircuitCountViewModel(projectId = params.get(),get(),get(),get(),get()) }
  viewModel { params->UpdateProjectWireSizeViewModel(projectId = params.get(),sizingType = params.get(),get(),get(),get(),get()) }
  viewModel { params->UpdateProjectUnitOfPowerViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params->UpdateProjectUnitOfWireSizeViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectConductorViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectMethodOfInstallationViewModel(projectId = params.get(),get(),get()) }
  viewModel { params-> UpdateProjectUnitOfTemperatureViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectUnitOfLengthViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectInsulationViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateProjectCodeViewModel(projectId = params.get(),get(),get()) }
  viewModel { AddProjectViewModel(get(),get()) }
  viewModel { params-> AddPanelViewModel(params.get(),get(),get(),get(),get()) }
  viewModel { params-> ProjectCenterViewModel(projectId = params.get(),get(),get(),get()) }
  viewModel { params->PanelDetailsViewModel(panelId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelCodeViewModel(panelId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateFeederViewModel(panelId = params.get(),get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedLengthViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedMethodOfInstallationViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedVoltDropViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedTemperatureViewModel(relationId = params.get(),environment = params.get(),get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedSoilResistivityViewModel(relationId = params.get(),get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedConductorViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedInsulationViewModel(relationId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdatePanelFeedCircuitCountViewModel(relationId = params.get(),get(),get(),get(),get()) }
  viewModel { params-> AddMotorViewModel(projectId = params.get(),get(),get(),get(),get()) }

  viewModel { params-> MotorDetailsViewModel(motorId = params.get(),get(),get()) }
  viewModel { params-> UpdateMotorCodeViewModel(motorId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorFeederViewModel(motorId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorPowerViewModel(motorId = params.get(),get(),get(),get()) }
  viewModel { params-> UpdateMotorDemandFactorViewMode(motorId = params.get(),get(),get(),get()) }


}
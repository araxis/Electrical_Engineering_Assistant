package com.vm.eea.ui.project.projectDetailsView

import androidx.lifecycle.ViewModel
import com.vm.eea.application.Environment
import com.vm.eea.application.PowerSystem
import com.vm.eea.application.project.*
import com.vm.eea.application.v
import com.vm.eea.ui.Destinations
import com.vm.eea.ui.NavigationManager
import com.vm.eea.ui.PropertyItem
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProjectDetailViewModel(
    private val projectId: ProjectId,
    private val getProjectDetails: IGetProjectDetails,
    private val navigationManager: NavigationManager,
):ContainerHost<ProjectDetailsState,Nothing>,ViewModel() {
    override val container: Container<ProjectDetailsState, Nothing>
    =container(ProjectDetailsState()){
        intent {
            getProjectDetails(projectId).collect {
                reduce { state.copy(
                    navigationItems = mapToPropertyList(it),
                    calculatedInfo = mapToCalculations(it)
                ) }
            }
        }
    }

    private fun mapToPropertyList(item: ProjectDetails):List<PropertyItem>{
        return listOf(
            PropertyItem("Code",item.code) {Destinations.UpdateProjectCode(projectId) },
            PropertyItem("Single phase voltage",item.singlePhaseVoltage.v()) {
                Destinations.UpdateProjectVoltage(projectId, PowerSystem.SinglePhase)
            },
            PropertyItem("Two phase voltage",item.threePhaseVoltage.v()) {
                Destinations.UpdateProjectVoltage(projectId,
                    PowerSystem.TwoPhase)
            },
            PropertyItem("Three phase voltage",item.threePhaseVoltage.v()) {
                Destinations.UpdateProjectVoltage(projectId,
                    PowerSystem.ThreePhase)
            },
            PropertyItem("Altitude",item.altitude.toFormatString()) {
                Destinations.ProjectAltitudeUpdate(projectId)
            },
            PropertyItem("Ambient Temperature",item.ambientTemperature()) {
                Destinations.ProjectTemperatureUpdate(projectId, Environment.Ambient)
            },
            PropertyItem("Ground Temperature",item.groundTemperature()) {
                Destinations.ProjectTemperatureUpdate(projectId, Environment.Ground)
            },
            PropertyItem("Soil Thermal Resistivity",item.soilResistivity()) {
                Destinations.ProjectSoilResistivityUpdate(projectId)
            },

            PropertyItem("Panel To Panel Max Volt Drop",item.panelToPanelMaxVoltDrop()) {
                Destinations.UpdateProjectMaxVoltDrop(projectId, RelationType.PanelToPanel)
            },
            PropertyItem("Panel To Motor Max Volt Drop",item.panelToMotorMaxVoltDrop()) {
                Destinations.UpdateProjectMaxVoltDrop(projectId, RelationType.PanelToMotor)
            },
            PropertyItem("Circuit In The Same Conduit",item.circuitInTheSameConduit.toString()) {
                Destinations.ProjectCircuitCountUpdate(projectId)
            },
//            PropertyItem("Max Wire Size",item.maxWireSize()) {
//                Destinations.UpdateProjectWireSize(projectId, IUpdateProject.WireSizeType.Max)
//            },
//            PropertyItem("Min Wire Size",item.minWireSize()) {
//                Destinations.UpdateProjectWireSize(projectId, IUpdateProject.WireSizeType.Min)
//            },

            PropertyItem("Conductor",item.conductor()) {
                Destinations.UpdateProjectConductor(projectId)
            },
            PropertyItem("Insulation",item.insulation()) {
                Destinations.UpdateProjectInsulation(projectId)
            },
            PropertyItem("Method Of Installation",item.methodOfInstallation()) {
                Destinations.UpdateProjectMethodOfInstallation(projectId)
            }

        )
    }

    private fun mapToCalculations(item: ProjectDetails):List<Pair<String,String>>{
        return listOf(
            "Current" to item.totalCurrent.toFormatString(),
            "Reactive power" to item.totalReactivePower.toFormatString(),
            "Apparent power" to item.totalApparentPower.toFormatString(),
            "Cos\uD835\uDF19" to item.cosPhi.toFormatString()
        )
    }

    fun onItemSelect(item: PropertyItem){
        navigationManager.navigate(item.updateViewRoute())
    }
}
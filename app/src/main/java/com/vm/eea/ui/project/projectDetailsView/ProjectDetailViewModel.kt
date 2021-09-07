package com.vm.eea.ui.project.projectDetailsView

import androidx.lifecycle.ViewModel
import com.vm.eea.domain.Environment
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.RelationType
import com.vm.eea.domain.project.GetProject
import com.vm.eea.domain.project.Project
import com.vm.eea.domain.project.WireSizeType
import com.vm.eea.ui.Destinations
import com.vm.eea.ui.PropertyItem
import com.vm.eea.ui.NavigationManager
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProjectDetailViewModel(
    private val projectId: Long,
    private val getProject: GetProject,
    private val navigationManager: NavigationManager,
):ContainerHost<ProjectDetailsState,Nothing>,ViewModel() {
    override val container: Container<ProjectDetailsState, Nothing> =container(ProjectDetailsState.init()){
        intent {
            getProject(projectId).collect {
                reduce { state.copy(navigationItems = mapToPropertyList(it)) }
            }
        }
    }

    private fun mapToPropertyList(item: Project):List<PropertyItem>{
        return listOf(
            PropertyItem("Code",item.code) {Destinations.UpdateProjectCode(projectId) },
            PropertyItem("1-Phase Voltage",item.singlePhaseVoltage()) {
                Destinations.UpdateProjectVoltage(projectId,
                    PowerSystem.SinglePhase)
            },
            PropertyItem("3-Phase Voltage",item.threePhaseVoltage()) {
                Destinations.UpdateProjectVoltage(projectId,
                    PowerSystem.ThreePhase)
            },
            PropertyItem("Altitude",item.altitude()) {
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
            PropertyItem("1-Phase Power factor",item.singlePhasePowerFactor()) {
                Destinations.UpdateProjectPowerFactor(projectId, PowerSystem.SinglePhase)
            },
            PropertyItem("3-Phase Power factor",item.threePhasePowerFactor()) {
                Destinations.UpdateProjectPowerFactor(projectId, PowerSystem.ThreePhase)
            },
            PropertyItem("Panel To Panel Max Volt Drop",item.panelToPanelMaxVoltDrop()) {
                Destinations.UpdateProjectMaxVoltDrop(projectId, RelationType.PanelToPanel)
            },
            PropertyItem("Panel To Motor Max Volt Drop",item.panelToMotorMaxVoltDrop()) {
                Destinations.UpdateProjectMaxVoltDrop(projectId, RelationType.PanelToMotor)
            },
            PropertyItem("Circuit In The Same Conduit",item.circuitInTheSameConduit()) {
                Destinations.ProjectCircuitCountUpdate(projectId)
            },
            PropertyItem("Max Wire Size",item.maxWireSize()) {
                Destinations.UpdateProjectWireSize(projectId,WireSizeType.Max)
            },
            PropertyItem("Min Wire Size",item.minWireSize()) {
                Destinations.UpdateProjectWireSize(projectId,WireSizeType.Min)
            },
            PropertyItem("Unit of Power",item.unitOfPower()) {
                Destinations.UpdateProjectUnitOfPower(projectId)
            },
            PropertyItem("Unit of Wire Size",item.unitOfWireSize()) {
                Destinations.UpdateProjectUnitOfWireSize(projectId)
            },
            PropertyItem("Conductor",item.conductor()) {
                Destinations.UpdateProjectConductor(projectId)
            },
            PropertyItem("Insulation",item.insulation()) {
                Destinations.UpdateProjectInsulation(projectId)
            },
            PropertyItem("Method Of Installation",item.methodOfInstallation()) {
                Destinations.UpdateProjectMethodOfInstallation(projectId)
            },
            PropertyItem("Unit Of Temperature",item.unitOfTemperature()) {
                Destinations.UpdateProjectUnitOfTemperature(projectId)
            },
            PropertyItem("Unit Of Length",item.unitOfLength()) {
                Destinations.UpdateProjectUnitOfLength(projectId)
            },

        )
    }

    fun onItemSelect(item: PropertyItem){
        navigationManager.navigate(item.updateViewRoute())
    }
}
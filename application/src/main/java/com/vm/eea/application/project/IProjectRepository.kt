package com.vm.eea.application.project

import com.vm.eea.application.*
import com.vm.eea.application.project.addProject.NewProject


interface IProjectRepository {
    suspend fun add(newProject: NewProject): ProjectId
    suspend  fun updateAltitude(projectId: ProjectId, value: Length)
    suspend fun updateCircuitCount(projectId: ProjectId, value: CircuitCount)
    suspend  fun updateCode(projectId: ProjectId, code: String, description: String)
    suspend fun updateConductor(projectId: ProjectId, value: Conductor)
    suspend fun updateInsulation(projectId: ProjectId, value: Insulation)
    suspend fun updatePanelToPanelMaxVoltDrop(projectId: ProjectId, value: VoltDrop)
    suspend fun updatePanelToMotorMaxVoltDrop(projectId: ProjectId, value: VoltDrop)
    suspend fun updateMethodOfInstallation(projectId: ProjectId, value: MethodOfInstallation)
    suspend fun updateThermalResistivity(projectId: ProjectId, value: ThermalResistivity)
    suspend fun updateAmbientTemperature(projectId: ProjectId, value: Temperature)
    suspend fun updateSoilTemperature(projectId: ProjectId, value: Temperature)
    suspend fun updateSinglePhaseVoltage(projectId: ProjectId, value: Double)
    suspend fun updateTwoPhaseVoltage(projectId: ProjectId, value: Double)
    suspend fun updateThreePhaseVoltage(projectId: ProjectId, value: Double)
    suspend fun updateMaxWireSize(projectId: ProjectId, value: WireSize)
    suspend fun updateMinWireSize(projectId: ProjectId, value: WireSize)




}
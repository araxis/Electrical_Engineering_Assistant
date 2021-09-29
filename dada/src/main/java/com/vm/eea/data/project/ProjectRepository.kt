package com.vm.eea.data.project

import com.vm.eea.application.*
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.project.addProject.NewProject
import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.ProjectEntity

class ProjectRepository(private val db: AppDatabase) : IProjectRepository {


    override suspend fun add(newProject: NewProject): ProjectId {
        val entity=   ProjectEntity(
                    code = newProject.code,
                    description =newProject.description,
                    altitude = newProject.altitude,
                    ambientTemperature =newProject.ambientTemperature,
                    unitOfWireSize = newProject.unitOfWireSize,
                    circuitInTheSameConduit = newProject.circuitInTheSameConduit,
                    conductor = newProject.conductor,
                    groundTemperature = newProject.groundTemperature,
                    insulation = newProject.insulation,
                    maxWireSize = newProject.maxWireSize,
                    methodOfInstallation = newProject.methodOfInstallation,
                    minWireSize = newProject.minWireSize,
                    isDeleted = false,
                    singlePhaseVoltageInVolt =  newProject.singlePhaseVoltage,
                    twoPhaseVoltageInVolt= newProject.twoPhaseVoltage,
                    panelToMotorMaxVoltDrop = newProject.panelToMotorMaxVoltDrop,
                    panelToPanelMaxVoltDrop = newProject.panelToPanelMaxVoltDrop,
                    singlePhaseCosPhi = newProject.singlePhaseCosPhi,
                    soilResistivity = newProject.soilResistivity,
                    standard = newProject.standard,
                    threePhaseCosPhi = newProject.threePhaseCosPhi,
                    twoPhaseCosPhi=newProject.twoPhaseCosPhi,
                    threePhaseVoltageInVolt = newProject.threePhaseVoltage,
                    unitOfLength = newProject.unitOfLength,
                    unitOfOfPower = newProject.unitOfPower,
                    unitOfTemperature = newProject.unitOfTemperature,
                    unitOfVoltage = newProject.unitOfOfVoltage
                )

          val id=  db.projectDao().insertProject(entity)
        return ProjectId(id)
    }
    override suspend fun updateCode(projectId: ProjectId, code: String,description:String) {
        db.projectDao().updateCode(projectId.id,code ,description)
    }
    override suspend fun updateSinglePhaseVoltage(projectId: ProjectId, value: Double) {
        db.projectDao().updateOnePhaseVoltage(projectId.id,value)
    }
    override suspend fun updateThreePhaseVoltage(projectId: ProjectId, value: Double) {
        db.projectDao().updateThreePhaseVoltage(projectId.id,value)
    }
    override suspend fun updateTwoPhaseVoltage(projectId: ProjectId, value: Double) {
        db.projectDao().updateTwoPhaseVoltage(projectId.id,value)
    }
    override suspend fun updateAltitude(projectId: ProjectId, value: Length) {
            db.projectDao().updateAltitude(projectId.id,value .value,value.unit )
    }
    override suspend fun updateCircuitCount(projectId: ProjectId, value: CircuitCount) {
        db.projectDao().updateCircuitInTheSameConduit(projectId.id,value.value)
    }
    override suspend fun updateMethodOfInstallation(projectId: ProjectId, value: MethodOfInstallation) {
        db.projectDao().updateMethodOfInstallation(projectId.id,value )
    }
    override suspend fun updateAmbientTemperature(projectId: ProjectId, value: Temperature) {
        db.projectDao().updateAmbientTemperature(projectId.id,value.value,value.unit )
    }
    override suspend fun updateSoilTemperature(projectId: ProjectId, value: Temperature) {
        db.projectDao().updateSoilTemperature(projectId.id,value.value,value.unit )
    }
    override suspend fun updateThermalResistivity(projectId: ProjectId, value: ThermalResistivity) {
        db.projectDao().updateSoilResistivity(projectId.id,value.value,value.unit )
    }
    override suspend fun updateConductor(projectId: ProjectId, value: Conductor) {
        db.projectDao().updateConductor(projectId.id,value )
    }
    override suspend fun updateInsulation(projectId: ProjectId, value: Insulation) {
        db.projectDao().updateInsulation(projectId.id,value )
    }
    override suspend fun updatePanelToPanelMaxVoltDrop(projectId: ProjectId, value: VoltDrop) {
        db.projectDao().updatePanelToPanelMaxVoltDrop(projectId.id,value.value )
    }
    override suspend fun updatePanelToMotorMaxVoltDrop(projectId: ProjectId, value: VoltDrop) {
        db.projectDao().updatePanelToMotorMaxVoltDrop(projectId.id,value.value )
    }
    override suspend fun updateMaxWireSize(projectId: ProjectId, value: WireSize) {
        db.projectDao().updateMaxCableSize(projectId.id,value.value,value.unit )
    }
    override suspend fun updateMinWireSize(projectId: ProjectId, value: WireSize) {
        db.projectDao().updateMinCableSize(projectId.id,value.value,value.unit )
    }
}
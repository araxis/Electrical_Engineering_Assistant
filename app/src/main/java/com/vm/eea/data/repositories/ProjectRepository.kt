package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.ProjectEntity
import com.vm.eea.domain.*
import com.vm.eea.domain.project.IProjectRepository
import com.vm.eea.domain.project.Project
import com.vm.eea.domain.project.SimpleProject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepository(private val db: AppDatabase) : IProjectRepository {


    override suspend fun addProject(project: Project):Long {
        val entity=   ProjectEntity(
                    code = project.code,
                    description =project.description,
                    altitude = project.altitude,
                    ambientTemperature =project.ambientTemperature,
                    unitOfWireSize = project.unitOfWireSize,
                    circuitInTheSameConduit = project.circuitInTheSameConduit,
                    conductor = project.conductor,
                    groundTemperature = project.groundTemperature,
                    insulation = project.insulation,
                    maxWireSize = project.maxWireSize,
                    methodOfInstallation = project.methodOfInstallation,
                    minWireSize = project.minWireSize,
                    isDeleted = false,
                    singlePhaseVoltage =  project.singlePhaseVoltage,
                    twoPhaseVoltage=project.twoPhaseVoltage,
                    panelToMotorMaxVoltDrop = project.panelToMotorMaxVoltDrop,
                    panelToPanelMaxVoltDrop = project.panelToPanelMaxVoltDrop,
                    singlePhasePowerFactor = project.singlePhasePowerFactor,
                    soilResistivity = project.soilResistivity,
                    standard = project.standard,
                    threePhasePowerFactor = project.threePhasePowerFactor,
                    twoPhasePowerFactor=project.twoPhasePowerFactor,
                    threePhaseVoltage = project.threePhaseVoltage,
                    unitOfLength = project.unitOfLength,
                    unitOfOfPower = project.unitOfPower,
                    unitOfTemperature = project.unitOfTemperature,
                    unitOfVoltage = project.unitOfOfVoltage
                )

          return  db.projectDao().insertProject(entity)
    }


    override fun getSimpleProjectsFlow(): Flow<List<SimpleProject>> {
        return db.projectDao().loadSimpleProjects()
    }

    override suspend fun getProject(projectId: Long): Project =
        db.projectDao().getProject(projectId).toDomain()





    override fun getProjectFlow(projectId: Long): Flow<Project> {
        return db.projectDao().getProjectFlow(projectId).map {it.toDomain()

        }
    }

    override suspend fun updateCode(projectId: Long, code: String,description:String) {
        db.projectDao().updateCode(projectId,code ,description)
    }



    override suspend fun updateOnePhaseVoltage(projectId: Long, value: Voltage) {
        db.projectDao().updateOnePhaseVoltage(projectId,value.value,value.unit )
    }

    override suspend fun updateThreePhaseVoltage(projectId: Long, value: Voltage) {
        db.projectDao().updateThreePhaseVoltage(projectId,value.value,value.unit )
    }

    override suspend fun updateTwoPhaseVoltage(projectId: Long, value: Voltage) {
        db.projectDao().updateTwoPhaseVoltage(projectId,value.value,value.unit )
    }

    override suspend fun updateUnitOfVoltage(projectId: Long, value: UnitOfVoltage) {
        db.projectDao().updateUnitOfVoltage(projectId,value  )
    }

    override suspend fun updateUnitOfLength(projectId: Long, value: UnitOfLength) {
        db.projectDao().updateUnitOfLength(projectId,value )
    }

    override suspend fun updateUnitOfPower(projectId: Long, value: UnitOfPower) {
        db.projectDao().updateUnitOfPower(projectId,value )
    }

    override suspend fun updateUnitOfTemperature(projectId: Long, value: UnitOfTemperature) {
        db.projectDao().updateUnitOfTemperature(projectId,value )
    }

    override suspend fun updateUnitOfWireSize(projectId: Long, value: UnitOfWireSize) {
        db.projectDao().updateCableSizeType(projectId,value )
    }

    override suspend fun updateAltitude(projectId: Long, value: Length) {
            db.projectDao().updateAltitude(projectId,value .value,value.unit )
    }


    override suspend fun updateMethodOfInstallation(projectId: Long, value: MethodOfInstallation) {
        db.projectDao().updateMethodOfInstallation(projectId,value )
    }

    override suspend fun updateAmbientTemperature(projectId: Long, value: Temperature) {
        db.projectDao().updateAmbientTemperature(projectId,value.value,value.unit )
    }

    override suspend fun updateSoilTemperature(projectId: Long, value: Temperature) {
        db.projectDao().updateSoilTemperature(projectId,value.value,value.unit )
    }

    override suspend fun updateSoilResistivity(projectId: Long, value: ThermalResistivity) {
        db.projectDao().updateSoilResistivity(projectId,value.value,value.unit )
    }

    override suspend fun updateConductor(projectId: Long, value: Conductor) {
        db.projectDao().updateConductor(projectId,value )
    }

    override suspend fun updateInsulation(projectId: Long, value: Insulation) {
        db.projectDao().updateInsulation(projectId,value )
    }

    override suspend fun updateSinglePhasePowerFactor(projectId: Long, value: Double) {
        db.projectDao().updateSinglePhasePowerFactor(projectId,value )
    }

    override suspend fun updateTwoPhasePowerFactor(projectId: Long, value: Double) {
        db.projectDao().updateTwoPhasePowerFactor(projectId,value )
    }

    override suspend fun updateThreePhasePowerFactor(projectId: Long, value: Double) {
        db.projectDao().updateThreePhasePowerFactor(projectId,value )
    }

    override suspend fun updatePanelToPanelMaxVoltDrop(projectId: Long, value: VoltDrop) {
        db.projectDao().updatePanelToPanelMaxVoltDrop(projectId,value.value )
    }

    override suspend fun updatePanelToMotorMaxVoltDrop(projectId: Long, value: VoltDrop) {
        db.projectDao().updatePanelToMotorMaxVoltDrop(projectId,value.value )
    }

    override suspend fun updateCircuitInTheSameConduit(projectId: Long, value: CircuitCount) {
        db.projectDao().updateCircuitInTheSameConduit(projectId,value.value )
    }

    override suspend fun updateMaxWireSize(projectId: Long, value: WireSize) {
        db.projectDao().updateMaxCableSize(projectId,value.value,value.unit )
    }

    override suspend fun updateMinWireSize(projectId: Long, value: WireSize) {
        db.projectDao().updateMinCableSize(projectId,value.value,value.unit )
    }

    override suspend fun updateStandard(projectId: Long, value: Standard) {
        db.projectDao().updateStandard(projectId,value)
    }


}
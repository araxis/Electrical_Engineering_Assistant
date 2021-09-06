package com.vm.eea.domain.project

import com.vm.eea.domain.*
import kotlinx.coroutines.flow.Flow

interface IProjectRepository {



    fun getSimpleProjectsFlow(): Flow<List<SimpleProject>>
    suspend fun getProject(projectId:Long): Project
    fun getProjectFlow(projectId: Long): Flow<Project>
    suspend fun addProject(project: Project):Long
    suspend fun updateCode(projectId: Long,code:String,description:String)
    suspend fun updateOnePhaseVoltage(projectId: Long,value:Voltage)
    suspend fun updateThreePhaseVoltage(projectId: Long,value:Voltage)
    suspend fun updateUnitOfVoltage(projectId: Long,value: UnitOfVoltage)
    suspend fun updateUnitOfLength(projectId: Long,value: UnitOfLength)
    suspend fun updateUnitOfPower(projectId: Long,value: UnitOfPower)
    suspend fun updateUnitOfTemperature(projectId: Long,value: UnitOfTemperature)
    suspend fun updateUnitOfWireSize(projectId: Long, value: UnitOfWireSize)

    suspend fun updateAltitude(projectId: Long,value:Length)
     suspend fun updateMethodOfInstallation(projectId: Long,value: MethodOfInstallation)
    suspend fun updateAmbientTemperature(projectId: Long,value: Temperature)
    suspend fun updateSoilTemperature(projectId: Long,value:Temperature)
    suspend fun updateSoilResistivity(projectId: Long,value: ThermalResistivity)
    suspend fun updateConductor(projectId: Long,value: Conductor)
    suspend fun updateInsulation(projectId: Long,value: Insulation)
    suspend fun updateSinglePhasePowerFactor(projectId: Long,value:Double)
    suspend fun updateThreePhasePowerFactor(projectId: Long,value:Double)
    suspend fun updatePanelToPanelMaxVoltDrop(projectId: Long,value:VoltDrop)
    suspend fun updatePanelToMotorMaxVoltDrop(projectId: Long,value:VoltDrop)
    suspend fun updateCircuitInTheSameConduit(projectId: Long,value:CircuitCount)
    suspend fun updateMaxWireSize(projectId: Long, value: WireSize)
    suspend fun updateMinWireSize(projectId: Long, value:WireSize)

    suspend fun updateStandard(projectId: Long,value: Standard)

    suspend fun updateTwoPhaseVoltage(projectId: Long, value: Voltage)
    suspend fun updateTwoPhasePowerFactor(projectId: Long, value: Double)
}
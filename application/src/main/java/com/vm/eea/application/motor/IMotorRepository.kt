package com.vm.eea.application.motor

import com.vm.eea.application.*
import com.vm.eea.application.project.ProjectId

interface IMotorRepository {

    suspend fun add(motor:Motor):MotorId
    suspend fun updateCode(loadId: MotorId, code: MotorCode, description: String)
    suspend fun updatePower(loadId: MotorId, power: Power)
    suspend fun updatePath(loadId: MotorId, path: SupplyPath)
    suspend fun updatePowerfactor(motorId: MotorId, powerfactor: CosPhi)
    suspend fun updateDemandFactor(motorId: MotorId, value: CosPhi)
    suspend fun updateEfficiency(motorId: MotorId, efficiency: Efficiency)
    suspend fun updateStartMode(motorId:MotorId,value: StartMode)
    suspend fun replaceSupplyPaths(projectId: ProjectId, oldStartPath: SupplyPath, newStartPath: SupplyPath)
}
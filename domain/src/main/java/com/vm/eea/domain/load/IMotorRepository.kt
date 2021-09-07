package com.vm.eea.domain.load

import com.vm.eea.domain.*
import kotlinx.coroutines.flow.Flow


interface IMotorRepository {

    fun get(projectId:Long): Flow<List<Motor>>
    suspend fun get(loadId: LoadId): Motor
    fun getMotorFlow(loadId: LoadId):Flow<Motor>
    suspend fun updateCode(loadId: LoadId, code: String, description: String)
    suspend fun updatePower(loadId: LoadId, power: Power)
    suspend fun add(item: Motor): LoadId
    suspend fun remove(item: Motor)
    suspend fun updatePath(loadId: LoadId, path: SupplyPath)
    suspend fun replaceStartPaths(projectId: Long, oldStartPath: SupplyPath, newStartPath: SupplyPath)
    suspend fun updatePowerfactor(motorId: LoadId, powerfactor: PowerFactor)
    suspend fun updateDemandFactor(motorId: LoadId, value: PowerFactor)
}
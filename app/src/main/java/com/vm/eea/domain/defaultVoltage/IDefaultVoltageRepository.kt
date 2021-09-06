package com.vm.eea.domain.defaultVoltage

import com.vm.eea.domain.PowerSystem
import kotlinx.coroutines.flow.Flow

interface IDefaultVoltageRepository {

    fun getValuesFlow(): Flow<List<DefaultVoltage>>
    fun getValuesFlow(system:PowerSystem):Flow<List<DefaultVoltage>>
    suspend fun insert(value:DefaultVoltage)
}
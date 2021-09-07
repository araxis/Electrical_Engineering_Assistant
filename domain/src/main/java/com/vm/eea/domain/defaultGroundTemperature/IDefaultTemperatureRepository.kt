package com.vm.eea.domain.defaultGroundTemperature

import com.vm.eea.domain.Environment
import kotlinx.coroutines.flow.Flow

interface IDefaultTemperatureRepository {

    fun getValuesFlow(): Flow<List<DefaultTemperature>>

    fun getValuesFlow(environment: Environment): Flow<List<DefaultTemperature>>

    suspend fun insert(value:DefaultTemperature)
}
package com.vm.eea.domain.defaultPowerfactor

import com.vm.eea.domain.PowerSystem
import kotlinx.coroutines.flow.Flow

interface IDefaultPowerfactorRepository {

    fun get(): Flow<List<DefaultPowerfactor>>
    fun get(system: PowerSystem):Flow<List<DefaultPowerfactor>>
    suspend fun isExist(value:Double,system: PowerSystem):Boolean
    suspend fun  insert(item:DefaultPowerfactor)
}
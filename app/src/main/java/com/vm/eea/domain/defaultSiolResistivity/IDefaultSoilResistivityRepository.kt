package com.vm.eea.domain.defaultSiolResistivity

import kotlinx.coroutines.flow.Flow

interface IDefaultSoilResistivityRepository {

    fun getValuesFlow(): Flow<List<DefaultSoilResistivity>>

    suspend fun insert(value: DefaultSoilResistivity)
}
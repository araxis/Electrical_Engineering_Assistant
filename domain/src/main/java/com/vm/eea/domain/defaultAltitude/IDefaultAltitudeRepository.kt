package com.vm.eea.domain.defaultAltitude

import kotlinx.coroutines.flow.Flow

interface IDefaultAltitudeRepository {

    fun getValuesFlow(): Flow<List<DefaultAltitude>>
    suspend fun insert(value:DefaultAltitude)
}
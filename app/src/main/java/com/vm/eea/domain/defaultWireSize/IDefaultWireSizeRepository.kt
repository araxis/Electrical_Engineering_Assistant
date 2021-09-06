package com.vm.eea.domain.defaultWireSize

import com.vm.eea.domain.UnitOfWireSize
import com.vm.eea.domain.WireSize
import kotlinx.coroutines.flow.Flow

interface IDefaultWireSizeRepository {
    fun get(): Flow<List<DefaultWireSize>>
    fun get(sizeType:UnitOfWireSize): Flow<List<DefaultWireSize>>
    suspend fun insert(item: DefaultWireSize)
    suspend fun isExist(value:WireSize):Boolean
}
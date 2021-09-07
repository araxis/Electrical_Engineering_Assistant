package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.DefaultWireSizeEntity
import com.vm.eea.domain.defaultWireSize.DefaultWireSize
import com.vm.eea.domain.UnitOfWireSize
import com.vm.eea.domain.WireSize
import com.vm.eea.domain.defaultWireSize.IDefaultWireSizeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultWireSizeRepository(private val db: AppDatabase): IDefaultWireSizeRepository {
    override fun get(): Flow<List<DefaultWireSize>> {
        return db.defaultWireSizeDao().getFlow().map {
            it.map { o->o.toDomain() }
        }
    }

    override fun get(sizeType: UnitOfWireSize): Flow<List<DefaultWireSize>> {
        return db.defaultWireSizeDao().getFlow(sizeType).map {
            it.map { o->o.toDomain() }
        }
    }

    override suspend fun insert(item: DefaultWireSize) {
        val entity=DefaultWireSizeEntity(item.wireSize,item.isCustom,item.id)
        db.defaultWireSizeDao().insert(entity)
    }

    override suspend fun isExist(value: WireSize): Boolean {
        return db.defaultWireSizeDao().isExist(value.value,value.unit)
    }
}
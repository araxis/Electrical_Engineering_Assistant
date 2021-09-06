package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.DefaultPowerFactorEntity
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.defaultPowerfactor.DefaultPowerfactor
import com.vm.eea.domain.defaultPowerfactor.IDefaultPowerfactorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultPowerfactorRepository(private val db:AppDatabase):IDefaultPowerfactorRepository {
    override fun get(): Flow<List<DefaultPowerfactor> >{
       return db.defaultPowerfactorDao().getFlow().map { it.map { item->item.toDomain() } }
    }

    override fun get(system: PowerSystem): Flow<List<DefaultPowerfactor>> {
        return db.defaultPowerfactorDao().getFlow(system).map { it.map {item-> item.toDomain() } }
    }

    override suspend fun isExist(value: Double, system: PowerSystem): Boolean {
        return db.defaultPowerfactorDao().isExist(value,system)
    }

    override suspend fun insert(item: DefaultPowerfactor) {
        val entity=DefaultPowerFactorEntity(item.value.value,item.system,item.isCustom,item.id)
        db.defaultPowerfactorDao().insert(entity)
    }
}
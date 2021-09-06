package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.DefaultSoilResistivityEntity
import com.vm.eea.domain.defaultSiolResistivity.DefaultSoilResistivity
import com.vm.eea.domain.defaultSiolResistivity.IDefaultSoilResistivityRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

class DefaultSoilResistivityRepository(private val db:AppDatabase):IDefaultSoilResistivityRepository {
    @FlowPreview
    override fun getValuesFlow(): Flow<List<DefaultSoilResistivity>> =
        db.defaultSoilResistivityDao().getDefaultsFlow().flatMapMerge {

            flow {
                emit(it.map { o-> DefaultSoilResistivity(o.value,o.isCustom,o.id) })
            }

        }




    override suspend fun insert(value: DefaultSoilResistivity) {
        if(db.defaultSoilResistivityDao().isExist(value.value.value,value.value.unit)) return
        val entity=DefaultSoilResistivityEntity(value.value,value.isCustom,0)
        db.defaultSoilResistivityDao().insert(entity)
    }
}
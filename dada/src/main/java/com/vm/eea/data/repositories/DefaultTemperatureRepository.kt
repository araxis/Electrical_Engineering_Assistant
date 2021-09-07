package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.DefaultTemperatureEntity
import com.vm.eea.domain.Environment
import com.vm.eea.domain.defaultGroundTemperature.DefaultTemperature
import com.vm.eea.domain.defaultGroundTemperature.IDefaultTemperatureRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

class DefaultTemperatureRepository(private val db: AppDatabase):IDefaultTemperatureRepository {
    @FlowPreview
    override fun getValuesFlow(): Flow<List<DefaultTemperature>> =
        db.defaultGroundTemperatureDao().getDefaultsFlow().flatMapMerge {

            flow {
                emit(it.map { o-> DefaultTemperature(o.value,o.isCustom,o.environment,o.id) })
            }

        }

    override fun getValuesFlow(environment: Environment): Flow<List<DefaultTemperature>> {
         return   db.defaultGroundTemperatureDao().getDefaultsFlow(environment).flatMapMerge {

             flow {
                 emit(it.map { o-> DefaultTemperature(o.value,o.isCustom,o.environment,o.id) })
             }

         }
    }


    override suspend fun insert(value: DefaultTemperature) {
        if(db.defaultGroundTemperatureDao().isExist(value.value.value,value.value.unit,value.environment)) return
        val entity=DefaultTemperatureEntity(value.value,value.environment,value.isCustom,0)
        db.defaultGroundTemperatureDao().insert(entity)
    }
}
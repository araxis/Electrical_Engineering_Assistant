package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.DefaultVoltageEntity
import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.defaultVoltage.DefaultVoltage
import com.vm.eea.domain.defaultVoltage.IDefaultVoltageRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

class DefaultVoltageRepository(private val db:AppDatabase):IDefaultVoltageRepository {
    @FlowPreview
    override fun getValuesFlow(): Flow<List<DefaultVoltage>> =
        db.defaultVoltageDao().getDefaultsFlow().flatMapMerge {

            flow {
                emit(it.map { o-> DefaultVoltage(o.voltage,o.system,o.isCustom,o.id) })
            }

        }

    override fun getValuesFlow(system: PowerSystem): Flow<List<DefaultVoltage>> =
        db.defaultVoltageDao().getDefaultsFlow(system).flatMapMerge {
            flow {
                emit(it.map { o-> DefaultVoltage(o.voltage,o.system,o.isCustom,o.id) })
            }

        }


    override suspend fun insert(value: DefaultVoltage) {
        val entity=DefaultVoltageEntity(value.voltage,value.system,value.isCustom,0)
        db.defaultVoltageDao().insert(entity)
    }
}
package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.DefaultAltitudeEntity
import com.vm.eea.domain.defaultAltitude.DefaultAltitude
import com.vm.eea.domain.defaultAltitude.IDefaultAltitudeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

class DefaultAltitudeRepository(private val db:AppDatabase):IDefaultAltitudeRepository {
    @FlowPreview
    override fun getValuesFlow(): Flow<List<DefaultAltitude>> =
        db.defaultAltitudeDao().getDefaultsFlow().flatMapMerge {

            flow {
                emit(it.map { o-> DefaultAltitude(o.value,o.isCustom,o.id) })
            }

        }




    override suspend fun insert(value: DefaultAltitude) {
        if(db.defaultAltitudeDao().isExist(value.value.value,value.value.unit)) return
        val entity=DefaultAltitudeEntity(value.value,value.isCustom,0)
        db.defaultAltitudeDao().insert(entity)
    }
}
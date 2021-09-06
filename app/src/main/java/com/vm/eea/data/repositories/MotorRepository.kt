package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.LoadEntity
import com.vm.eea.domain.Power
import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.SupplyPath
import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.load.LoadId
import com.vm.eea.domain.load.LoadType
import com.vm.eea.domain.load.Motor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MotorRepository(private val db:AppDatabase): IMotorRepository {
    override fun get(projectId: Long): Flow<List<Motor>> {
        return  db.loadDao().all(projectId).map { it.map { o->o.toMotorDomain() }}
    }

    override suspend fun get(loadId: LoadId): Motor {
        return db.loadDao().get(loadId.id, LoadType.Motor).toMotorDomain()
    }

    override  fun getMotorFlow(loadId: LoadId): Flow<Motor> {
        return db.loadDao().getLoadFlow(loadId.id,LoadType.Motor).map { it.toMotorDomain() }
    }

    override suspend fun updateCode(loadId: LoadId, code: String, description: String) {
        db.loadDao().updateCode(loadId.id,code,description)
    }

    override suspend fun updatePower(loadId: LoadId, power: Power) {
        db.loadDao().updatePower(loadId.id,power.value,power.unit)
    }

    override suspend fun replaceStartPaths(projectId:Long, oldStartPath: SupplyPath, newStartPath: SupplyPath){
        db.loadDao().replaceStartPaths(projectId,oldStartPath.path,newStartPath.path)
    }

    override suspend fun updatePowerfactor(motorId: LoadId, powerfactor: PowerFactor) {
        db.loadDao().updatePowerfactor(motorId.id,powerfactor.value)
    }

    override suspend fun updateDemandFactor(motorId: LoadId, value: PowerFactor) {
        db.loadDao().updateDemandFactor(motorId.id,value.value)
    }

    override suspend fun add(item: Motor):LoadId {
        val entity= with(item){
            LoadEntity(
            projectId = projectId,
            description = description,
            demandFactor=demandFactor,
            code = code,
            system = system,
            efficiency = efficiency,
            normal = item.feedingMode.normal,
            emergency=item.feedingMode.emergency,
            loadType = LoadType.Motor,
            power = power,
            powerfactor = powerfactor,
            powerSupplyPath = powerSupplyPath.path,
            serviceMode = serviceMode,
)
        }
       val id= db.loadDao().insert(entity)
        return LoadId(id)
    }

    override suspend fun remove(item: Motor) {
     //  db.loadDao().deleteById(item.id)
    }

    override suspend fun updatePath(loadId: LoadId, path: SupplyPath) {
       db.loadDao().updatePath(loadId.id,path.path)
    }
}
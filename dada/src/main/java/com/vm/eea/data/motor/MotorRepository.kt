package com.vm.eea.data.motor

import com.vm.eea.application.*
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.Motor
import com.vm.eea.application.motor.MotorCode
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.LoadEntity
import com.vm.eea.data.model.LoadType

class MotorRepository(private val db: AppDatabase): IMotorRepository {



    override suspend fun updateStartMode(motorId: MotorId,value: StartMode) {
        db.loadDao().updateStartMode(motorId.id,value)
    }

    override suspend fun replaceSupplyPaths(projectId: ProjectId,oldStartPath: SupplyPath,newStartPath: SupplyPath) {
        db.loadDao().replaceStartPaths(projectId(),oldStartPath.path,newStartPath.path)
    }


    override suspend fun updateCode(loadId: MotorId, code: MotorCode, description: String) {
        db.loadDao().updateCode(loadId.id,code.value,description)
    }

    override suspend fun updatePower(loadId: MotorId, power: Power) {
        db.loadDao().updatePower(loadId.id,power.value,power.unit)
    }



    override suspend fun updatePowerfactor(motorId: MotorId, powerfactor: CosPhi) {
        db.loadDao().updatePowerfactor(motorId.id,
            powerfactor.value,
            powerfactor.sinPhi(),powerfactor.tanPhi())
    }

    override suspend fun updateDemandFactor(motorId: MotorId, value: CosPhi) {
        db.loadDao().updateDemandFactor(motorId.id,value.value,value.tanPhi())
    }

    override suspend fun updateEfficiency(motorId: MotorId, efficiency: Efficiency) {
        db.loadDao().updateEfficiency(motorId.id,efficiency.value)
    }

    override suspend fun add(motor: Motor): MotorId {
        val entity= with(motor){
            LoadEntity(
            projectId = projectId(),
            description = description,
            demandFactorCosPhi= demandFactor.value,
            demandFactorTanPhi=demandFactor.tanPhi(),
            code = code.value,
            system = system,
            startMode=startMode,
            efficiency = efficiency,
            normal = motor.feedingMode.normal,
            emergency=motor.feedingMode.emergency,
            loadType = LoadType.Motor,
            power = power,
            cosPhi =powerfactor.value,
            powerSupplyPath = powerSupplyPath.path,
            serviceMode = serviceMode,
            powerInWatt = motor.powerInWatt,
            sinPhi = motor.powerfactor.sinPhi(),
            tanPhi = motor.powerfactor.tanPhi(),
            applyLocalCosPhiCorrection = motor.applyLocalCosPhiCorrection
)
        }
       val id= db.loadDao().insert(entity)
        return MotorId(id)
    }



    override suspend fun updatePath(loadId: MotorId, path: SupplyPath) {
       db.loadDao().updatePath(loadId.id,path.path)
    }
}
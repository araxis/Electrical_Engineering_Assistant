package com.vm.eea.data.motor

import com.vm.eea.application.*
import com.vm.eea.application.motor.*
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.FullMotorView

class MotorRepository(private val db: AppDatabase,
                      private val transactionProvider:ITransactionProvider): IMotorRepository {




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
            powerInWatt = (motor.power to Power.Unit.W).value,
            sinPhi = motor.powerfactor.sinPhi(),
            tanPhi = motor.powerfactor.tanPhi(),
            applyLocalCosPhiCorrection = motor.applyLocalCosPhiCorrection,
                protectionType = motor.protectionType,
                hasOverLoadRelay = motor.hasOverLoadRelay,
                isBiDirect = motor.isBiDirect,
                ratedSpeedRpm = motor.ratedSpeed.value
)
        }
       val id= db.loadDao().insert(entity)
        return MotorId(id)
    }

    override suspend fun remove(motorId: MotorId) {
        transactionProvider.runAsTransaction {
            db.panelToMotorRelationDao().deleteRelationByLoadId(motorId.id)
            db.loadDao().deleteById(motorId.id)
        }

    }

    override suspend fun getMotor(motorId: MotorId): Motor {
        val entity=db.loadDao().get(motorId.id, LoadType.Motor)
        val motor= with(entity) {
            Motor(projectId = ProjectId(projectId),
                motorId = MotorId(id),
                MotorCode(code),
                description = description,
                power = power,
                CosPhi(cosPhi),
                CosPhi(demandFactorCosPhi),
                applyLocalCosPhiCorrection = applyLocalCosPhiCorrection,
                efficiency = efficiency,
                system = system,
                startMode = startMode,
                Speed(ratedSpeedRpm),
                serviceMode = serviceMode,
                SupplyPath(powerSupplyPath),
                FeedingMode(normal, emergency),
                powerInWatt = powerInWatt,
                isBiDirect = isBiDirect,
                hasOverLoadRelay = hasOverLoadRelay,
                protectionType = protectionType)
        }
            return motor


    }

    override suspend fun getMotorInfo(motorId: MotorId): MotorInfo {
        val entity = db.loadDao().getFullMotor(motorId.id)
        return mapToMotorInfo(entity)
    }

    private fun mapToMotorInfo(entity: FullMotorView): MotorInfo {
        val motor = with(entity) {
            MotorInfo(projectId = ProjectId(projectId),
                motorId = MotorId(loadId),
                MotorCode(code),
                description = description,
                power = power,
                CosPhi(cosPhi),
                CosPhi(demandFactorCosPhi),
                efficiency = efficiency,
                system = system,
                startMode = startMode,
                ratedSpeed= Speed(ratedSpeedRpm),
                serviceMode = serviceMode,
                powerSupplyPath= SupplyPath(powerSupplyPath),
                feedingMode= FeedingMode(normal, emergency),
                powerInWatt = powerInWatt,
                isBiDirect = isBiDirect,
                hasOverLoadRelay = hasOverLoadRelay,
                protectionType = protectionType,
                voltage = voltageInVolt.v,
            apparentPower = apparentPower.VA,reactivePower = reactivePower.VAr,
            current = current.A)
        }
        return motor
    }

    override suspend fun getPanelMotors(panelId: PanelId): List<MotorInfo> {
        val entities=db.loadDao().getPanelMotors(panelId.id)
        return entities.map { mapToMotorInfo(it) }
    }

    override suspend fun getVoltage(motorId: MotorId):Voltage{

        return db.loadDao().getMotorVoltage(motorId.id).v
    }

    override suspend fun update(motor: Motor) {
        val entity= with(motor){
            LoadEntity(description=description,
            projectId = projectId.id,
            system = system,
            protectionType = protectionType,
            isBiDirect = isBiDirect,
            hasOverLoadRelay = hasOverLoadRelay,
            efficiency = efficiency,
            startMode = startMode,
            power = power,
            ratedSpeedRpm = ratedSpeed.value,
            cosPhi = powerfactor.value,
            serviceMode = serviceMode,
            code = code.value,
            id = motorId.id,
            powerSupplyPath = powerSupplyPath.path,
            applyLocalCosPhiCorrection = applyLocalCosPhiCorrection,
            demandFactorCosPhi = demandFactor.value,
            tanPhi = powerfactor.tanPhi(),
            sinPhi = powerfactor.sinPhi(),
            powerInWatt = (power to Power.Unit.W).value,
            loadType = LoadType.Motor,
            normal = feedingMode.normal,
            emergency = feedingMode.emergency,
            demandFactorTanPhi = demandFactor.tanPhi())
        }
        db.loadDao().update(entity)
    }




}
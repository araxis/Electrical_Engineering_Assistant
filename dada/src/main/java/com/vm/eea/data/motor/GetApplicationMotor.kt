package com.vm.eea.data.motor

import com.vm.eea.application.*
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.calculators.applicationProject.motorProject.ApplicationMotor
import com.vm.eea.application.calculators.applicationProject.motorProject.IApplicationProjectCodeResolver
import com.vm.eea.application.calculators.applicationProject.motorProject.IGetApplicationMotor
import com.vm.eea.data.AppDatabase

class GetApplicationMotor(
    private val db: AppDatabase,
    private val applicationProjectCodeResolver: IApplicationProjectCodeResolver
): IGetApplicationMotor {

    override suspend fun invoke(): ApplicationMotor {
        val projectCode=applicationProjectCodeResolver.motorProjectCode()
        val motorEntity=db.loadDao().getFullMotor(projectCode)
        return with(motorEntity){
            ApplicationMotor(projectCode=projectCode,projectId.toProjectId(),
                loadId.toMotorId() ,power,Voltage(voltageInVolt,Voltage.Unit.V),
                CosPhi(cosPhi), CosPhi(demandFactorCosPhi),applyLocalCosPhiCorrection,efficiency,system,
                startMode,
                Speed(ratedSpeedRpm),serviceMode,
                SupplyPath(powerSupplyPath),
                FeedingMode(normal, emergency),powerInWatt,isBiDirect,hasOverLoadRelay,protectionType)
        }
    }

    override suspend fun invoke(motorId:MotorId): ApplicationMotor {
        val motorEntity=db.loadDao().getFullMotor(motorId.id)
        return with(motorEntity){
            ApplicationMotor(projectCode = projectCode,projectId.toProjectId(),
                loadId.toMotorId() ,power,Voltage(voltageInVolt,Voltage.Unit.V),
                CosPhi(cosPhi), CosPhi(demandFactorCosPhi),applyLocalCosPhiCorrection,efficiency,system,
                  startMode,
                Speed(ratedSpeedRpm),serviceMode,
                SupplyPath(powerSupplyPath),
                FeedingMode(normal, emergency),powerInWatt,isBiDirect,hasOverLoadRelay,protectionType)
        }
    }
}
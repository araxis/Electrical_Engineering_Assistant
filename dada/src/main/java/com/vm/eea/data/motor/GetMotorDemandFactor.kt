package com.vm.eea.data.motor

import com.vm.eea.application.CosPhi
import com.vm.eea.application.motor.IGetMotorDemandFactor
import com.vm.eea.application.motor.MotorId
import com.vm.eea.data.AppDatabase

class GetMotorDemandFactor(private val db:AppDatabase):IGetMotorDemandFactor {

    override suspend operator fun invoke(motorId: MotorId):CosPhi{
        return db.motorReadDao().getDemandFactor(motorId.id)
            .let { CosPhi(it) }
    }
}
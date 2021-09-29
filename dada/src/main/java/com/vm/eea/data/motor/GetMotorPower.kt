package com.vm.eea.data.motor

import com.vm.eea.application.Power
import com.vm.eea.application.motor.IGetMotorPower
import com.vm.eea.application.motor.MotorId
import com.vm.eea.data.AppDatabase

class GetMotorPower(private val db: AppDatabase):IGetMotorPower {
    override suspend fun invoke(motorId: MotorId): Power = db.motorReadDao().getPower(motorId.id)

}